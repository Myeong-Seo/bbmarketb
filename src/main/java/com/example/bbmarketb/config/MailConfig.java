package com.example.bbmarketb.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com"); // 또는 사용하는 SMTP 주소
        mailSender.setPort(587);

        mailSender.setUsername("zaxw5965@gmail.com"); // 발신자 이메일 주소
        mailSender.setPassword("wvtg gvcs jbus ozkt");    // 앱 비밀번호 (계정 비번 아님)

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true"); // 개발 중 디버깅용

        return mailSender;
    }
}
