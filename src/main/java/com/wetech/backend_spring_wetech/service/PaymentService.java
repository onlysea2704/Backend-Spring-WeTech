package com.wetech.backend_spring_wetech.service;

import com.wetech.backend_spring_wetech.dto.WebhookPayload;
import com.wetech.backend_spring_wetech.entity.ListItem;
import com.wetech.backend_spring_wetech.entity.MyCourse;
import com.wetech.backend_spring_wetech.entity.MyProcedure;
import com.wetech.backend_spring_wetech.entity.Transaction;
import com.wetech.backend_spring_wetech.repository.ListItemRepository;
import com.wetech.backend_spring_wetech.repository.MyCourseRepository;
import com.wetech.backend_spring_wetech.repository.MyProcedureRepository;
import com.wetech.backend_spring_wetech.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PaymentService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ListItemRepository listItemRepository;
    @Autowired
    private MyCourseRepository myCourseRepository;
    @Autowired
    MyProcedureRepository myProcedureRepository;

    public boolean createTransaction(Transaction transaction, List<ListItem> listItem) {
        try {
            Transaction newTransaction = transactionRepository.save(transaction);
            for (ListItem item : listItem) {
                item.setIdTransaction(newTransaction.getIdTransaction());
            }
            listItemRepository.saveAll(listItem);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyTransaction(WebhookPayload webhookPayload) {

        Transaction transaction = transactionRepository.findByCode(webhookPayload.getCode());

        if (transaction.getTransferAmount() <= webhookPayload.getTransferAmount()) {
            List<ListItem> listItems = listItemRepository.findByIdTransaction(transaction.getIdTransaction());
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
