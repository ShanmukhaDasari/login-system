package com.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("dasarissrk2002@gmail.com"); // Same Gmail used above
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message); // Actual sending
    }
}
