package com.sdp3.main.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.*;
import org.springframework.mail.*;


@Service
public class EmailSenderService {
    
    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String toEmail,String body,String subject){
        SimpleMailMessage message = new SimpleMailMessage();
        String from = "pradeep30061@gmail.com";
        message.setFrom(from);
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
    }
}
