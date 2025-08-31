package com.wetech.backend_spring_wetech.controller;

import com.wetech.backend_spring_wetech.dto.WebhookPayload;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth/webhook")
public class WebhookController {

    @PostMapping("/verify-payment")
    public ResponseEntity<Map<String, Object>> receiveWebhook(@RequestBody WebhookPayload payload) {
        // In ra console để test
        System.out.println("Nhận webhook từ SePay:");
        System.out.println("ID: " + payload.getId());
        System.out.println("Ngân hàng: " + payload.getGateway());
        System.out.println("Số tiền: " + payload.getTransferAmount());
        System.out.println("Nội dung: " + payload.getContent());
        System.out.println("Nội dung: " + payload.getCode());


        // TODO: xử lý lưu DB hoặc logic nghiệp vụ ở đây

        // Trả về JSON
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Webhook received successfully");

        return ResponseEntity.ok(response);
    }
}
