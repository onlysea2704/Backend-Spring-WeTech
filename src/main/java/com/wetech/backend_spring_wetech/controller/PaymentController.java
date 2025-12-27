package com.wetech.backend_spring_wetech.controller;

import com.wetech.backend_spring_wetech.dto.InputCreateTransactionDto;
import com.wetech.backend_spring_wetech.dto.WebhookPayload;
import com.wetech.backend_spring_wetech.entity.Course;
import com.wetech.backend_spring_wetech.entity.ListItem;
import com.wetech.backend_spring_wetech.entity.Transaction;
import com.wetech.backend_spring_wetech.entity.User;
import com.wetech.backend_spring_wetech.service.PaymentService;
import com.wetech.backend_spring_wetech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public ResponseEntity<?> getPaymentByCode(@RequestParam("idTransaction") Long idTransaction) {
        Transaction transaction = paymentService.getTransactionByCode(idTransaction);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/get-list-item-by-id")
    public ResponseEntity<?> getListItemById(@RequestParam("idTransaction") Long idTransaction) {
        List<Course> courses = paymentService.getListItemById(idTransaction);
        return ResponseEntity.ok(courses);
    }

    @PostMapping("/update-info")
    public ResponseEntity<?> updatePaymentInfo(
            @RequestParam("idTransaction") Long idTransaction,
            @RequestBody Transaction transaction) {
        Transaction updatedTransaction = paymentService.updateInfo(idTransaction, transaction);
        return ResponseEntity.ok(updatedTransaction);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createPayment(
            @RequestBody InputCreateTransactionDto inputCreateTransactionDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = (User) userService.loadUserByUsername(username);
        Transaction newTransaction = paymentService.createTransaction(inputCreateTransactionDto.getTransaction(), inputCreateTransactionDto.getListItems(), user);
        return ResponseEntity.ok(newTransaction);
    }

    @PostMapping("/webhook/verify-payment")
    public ResponseEntity<Object> verifyTransaction(@RequestBody WebhookPayload payload) {
        boolean statusPayment = paymentService.verifyTransaction(payload);
        System.out.println(statusPayment);
        return ResponseEntity.ok(statusPayment);
    }
}
