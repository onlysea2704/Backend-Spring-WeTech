package com.wetech.backend_spring_wetech.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendSimpleMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        // message.setFrom(...) // optional
        mailSender.send(message);
    }

    public void sendConsultingRequestEmail(String toAdminEmail, String name, String email, String phone, String service) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toAdminEmail);
        message.setSubject("Có người dùng mới yêu cầu tư vấn");
        String text = """
                Có một khách hàng gửi yêu cầu tư vấn:

                - Tên: %s
                - Email: %s
                - Số điện thoại: %s
                - Dịch vụ quan tâm: %s

                Vui lòng liên hệ lại sớm nhất!
                """.formatted(name, email, phone, service);
        message.setText(text);
        mailSender.send(message);
    }
}
