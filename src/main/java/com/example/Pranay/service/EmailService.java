package com.example.Pranay.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import static java.awt.SystemColor.text;
@Service
public class EmailService {

    private JavaMailSender mailSender;

    // for simple mail
    public String sendPlainTextMail(String to,String subject,String text){
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        mailSender.send(mailMessage);
        return "Plain text email sent Successfully ";
    }

    public String sendHtmlMail(String to,String subject,String htmlContent)
            throws MessagingException {
        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper helper= new MimeMessageHelper(mimeMessage,true,"UTF-8");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent,true);
        mailSender.send(mimeMessage);
        return "Html email sent successfully" ;
    }
}
