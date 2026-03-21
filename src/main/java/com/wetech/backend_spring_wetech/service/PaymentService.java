package com.wetech.backend_spring_wetech.service;

import com.wetech.backend_spring_wetech.dto.WebhookPayload;
import com.wetech.backend_spring_wetech.entity.*;
import com.wetech.backend_spring_wetech.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class PaymentService {
    private TransactionRepository transactionRepository;
    private ListItemRepository listItemRepository;
    private MyCourseRepository myCourseRepository;
    private CourseRepository courseRepository;
    private MyProcedureRepository myProcedureRepository;
    private ProcedureRepository procedureRepository;
    private ProcedureService procedureService;
    private CartRepository cartRepository;
    private SimpMessagingTemplate messagingTemplate;

    public Transaction getTransactionByCode(Long idTransaction){
        return transactionRepository.findFirstByIdTransaction(idTransaction);
    }

    public List<Course> getListItemById(Long idTransaction){
        List<ListItem> listCourse = listItemRepository.findByIdTransaction(idTransaction);
        List<Course> courses = new ArrayList<>();
        for (ListItem item : listCourse) {
            Course course = courseRepository.findFirstByCourseId(item.getIdCourse());
            courses.add(course);
        }
        return courses;
    }

    public Transaction updateInfo(Long idTransaction, Transaction transaction) {
        Transaction transaction1 = transactionRepository.findFirstByIdTransaction(idTransaction);
        transaction1.setFullName(transaction.getFullName());
        transaction1.setEmail(transaction.getEmail());
        transaction1.setPhone(transaction.getPhone());
        transaction1.setTaxCode(transaction.getTaxCode());
        transaction1.setCompanyName(transaction.getCompanyName());
        transaction1.setCompanyAddress(transaction.getCompanyAddress());
//        transaction1.setAddress(transaction.getAddress());
        return transactionRepository.save(transaction1);
    }

    public Transaction createTransaction(Transaction transaction, List<ListItem> listItem, User user) {
        try {
            LocalDateTime dateTime = LocalDateTime.now();
            transaction.setUserId(user.getUserId());
            transaction.setTransactionDate(dateTime);
            Transaction newTransaction = transactionRepository.save(transaction);
            for (ListItem item : listItem) {
                item.setIdTransaction(newTransaction.getIdTransaction());
            }
            listItemRepository.saveAll(listItem);
            return newTransaction;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean verifyTransaction(WebhookPayload webhookPayload) {

        LocalDateTime now = LocalDateTime.now();
        Transaction transaction = transactionRepository.findByCode(webhookPayload.getCode());

        if (transaction.getTransferAmount() <= webhookPayload.getTransferAmount()) {
            List<ListItem> listItems = listItemRepository.findByIdTransaction(transaction.getIdTransaction());
            transaction.setStatus("SUCCESS");
            transaction.setTransactionDate(now);
            transactionRepository.save(transaction);

            // Socket gửi đến FE
            messagingTemplate.convertAndSend("/topic/payment/" + transaction.getUserId(),
                    Map.of("message", "Thanh toán thành công"));

            for (ListItem item : listItems) {
                if(item.getIdCourse() != null){
                    MyCourse myCourse = new MyCourse();
                    myCourse.setCourseId(item.getIdCourse());
                    myCourse.setUserId(transaction.getUserId());
                    myCourseRepository.save(myCourse);

                    Cart existCourseInCart = cartRepository.findFirstByUserIdAndCourseId(transaction.getUserId(), item.getIdCourse());
                    if(existCourseInCart!=null){
                        cartRepository.delete(existCourseInCart);
                    }
                }
                else {
                    MyProcedure myProcedure = myProcedureRepository.findByUserIdAndProcedureId(transaction.getUserId(), item.getIdProcedure());
                    if (myProcedure != null) {
                        myProcedure.setStatus(MyProcedure.Status.PAID);
                        myProcedureRepository.save(myProcedure);
                    }
                    Procedure procedure = procedureService.findById(item.getIdProcedure());
                    procedure.setNumberRegister(procedure.getNumberRegister() == null ? 1 : procedure.getNumberRegister() + 1);
                    procedureRepository.save(procedure);
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
