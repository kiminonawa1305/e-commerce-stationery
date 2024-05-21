package com.lamnguyen.stationery_kimi.config;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.util.Date;

@Configuration
public class SendMailConfig {
    @Autowired
    private JavaMailSender emailSender;

    @Bean
    public MimeMessage createMimeMessage() {
        return emailSender.createMimeMessage();
    }

    @Bean
    public MimeMessageHelper getSendMailHelper(MimeMessage message) throws MessagingException {
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("kiminonawa@gmail.com");
        helper.setSentDate(new Date());
        return helper;
    }
}
