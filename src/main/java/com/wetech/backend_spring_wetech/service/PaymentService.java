package com.wetech.backend_spring_wetech.service;

import com.wetech.backend_spring_wetech.dto.WebhookPayload;
import com.wetech.backend_spring_wetech.entity.*;
import com.wetech.backend_spring_wetech.repository.ListItemRepository;
import com.wetech.backend_spring_wetech.repository.MyCourseRepository;
import com.wetech.backend_spring_wetech.repository.MyProcedureRepository;
import com.wetech.backend_spring_wetech.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import java.util.Map;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ListItemRepository listItemRepository;
    @Autowired
    private MyCourseRepository myCourseRepository;
    @Autowired
    MyProcedureRepository myProcedureRepository;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public  Transaction getTransactionByCode(String code){
        return transactionRepository.findByCode(code);
    }

    public Transaction createTransaction(Transaction transaction, List<ListItem> listItem, User user) {
        try {
            transaction.setUserId(user.getUserId());
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

        Transaction transaction = transactionRepository.findByCode(webhookPayload.getCode());

        if (transaction.getTransferAmount() <= webhookPayload.getTransferAmount()) {
            List<ListItem> listItems = listItemRepository.findByIdTransaction(transaction.getIdTransaction());
            transaction.setStatus("SUCCESS");
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
                }
                else {
                    MyProcedure myProcedure = new MyProcedure();
                    myProcedure.setProcedureId(item.getIdProcedure());
                    myProcedure.setUserId(transaction.getUserId());
                    myProcedureRepository.save(myProcedure);
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
