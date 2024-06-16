package com.lamnguyen.stationery_kimi.service.impl;

import com.lamnguyen.stationery_kimi.service.IEmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

@Service
public class EmailServiceImpl implements IEmailService {
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private MimeMessageHelper helper;
    @Autowired
    private MimeMessage message;

    @Value("${email.time-out}")
    private int timeOut;

    public void sendMessage(String to, String subject, String text) {
        try {
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);

            helper.addInline("email_open", ResourceUtils.getFile("classpath:static/images/email_open.png"));

            emailSender.send(message);
        } catch (MessagingException | RuntimeException | FileNotFoundException e) {
            System.out.println(e);
        }
    }

    public String getTemplate(String name, String code) {
        StringBuffer template = new StringBuffer();
        template.append("<div style=\"text-align: center;\">")
                .append("<h1>Chào mừng bạn đến với Stationery Kimi</h1>")
                .append("<h2>Xác thực tài khoàn</h2>")
                .append("<div><img src=\"cid:email_open\"></div>")
                .append("<h3><strong >Xin chào ")
                .append(name)
                .append(",</strong></h3>")
                .append("<p>Vui lòng nhập mã xác thực bên dưới để hoàn tất quá trình đăng nhập.</p>")
                .append("<p style=\"color: gray;\">Mã xác thật chỉ khả dụng trong vòng " + timeOut + " phút.</p>")
                .append("<div style=\"margin: 50px 0; justify-content: center;\">");
        for (char number : code.toCharArray())
            template.append("<span style=\"padding: 15px; border: 1px gray solid; border-radius: 10px; font-size: 25px; margin: 0 5px;\">")
                    .append(number)
                    .append("</span>");
        template.append("</div>")
                .append("<p>Cảm ơn bạn đã tin tưởng và lựa chọn dịch dụ bên chúng tôi.</p>")
                .append("</div>");

        return template.toString();
    }
}
