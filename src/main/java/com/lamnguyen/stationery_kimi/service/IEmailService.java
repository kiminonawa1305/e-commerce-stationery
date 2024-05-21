package com.lamnguyen.stationery_kimi.service;

import jakarta.mail.MessagingException;

import java.io.FileNotFoundException;

public interface IEmailService {
    void sendMessage(String to, String subject, String text) throws MessagingException, FileNotFoundException;

    String getTemplate(String name, String code);
}
