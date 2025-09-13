package com.wetech.backend_spring_wetech.controller;

import com.wetech.backend_spring_wetech.dto.WebhookPayload;
import com.wetech.backend_spring_wetech.entity.ListItem;
import com.wetech.backend_spring_wetech.entity.Transaction;
import com.wetech.backend_spring_wetech.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<Object> createPayment(
            @RequestBody Transaction transaction,
            @RequestBody List<ListItem> listItems) {
        boolean statusCreated = paymentService.createTransaction(transaction, listItems);
        return ResponseEntity.ok(statusCreated);
    }

    @PostMapping("/webhook/verify-payment")
    public ResponseEntity<Object> verifyTransaction(@RequestBody WebhookPayload payload) {
        boolean statusPayment = paymentService.verifyTransaction(payload);
        return ResponseEntity.ok(statusPayment);
    }
}
