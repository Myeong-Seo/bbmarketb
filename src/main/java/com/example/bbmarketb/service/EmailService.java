package com.example.bbmarketb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendResetPasswordEmail(String to, String token) {
        String subject = "비밀번호 재설정 링크";
        String link = "http://localhost:5173/reset-password?token=" + token;
        String content = "비밀번호 재설정 링크를 클릭하세요: " + link;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("zaxw5965@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }
}
