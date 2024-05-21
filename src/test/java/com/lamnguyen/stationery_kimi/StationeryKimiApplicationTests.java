package com.lamnguyen.stationery_kimi;

import com.lamnguyen.stationery_kimi.service.IEmailService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;

@SpringBootTest
class StationeryKimiApplicationTests {
    @Autowired
    IEmailService iEmailService;

    @Test
    public void testSendSimpleMessage() throws MessagingException, FileNotFoundException {
        String name = "Lam Nguyen";
        String coade = "123456";
        String template = iEmailService.getTemplate(name, coade);
        iEmailService.sendMessage("maitien13052003@gmail.com", "Xác thực email", template);
    }
}
