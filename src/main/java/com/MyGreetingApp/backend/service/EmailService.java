package com.MyGreetingApp.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailService {

//    private final Dotenv dotenv;
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String senderEmail;
    public EmailService(JavaMailSender mailSender)
    {
//        this.dotenv=_env;
        this.mailSender = mailSender;
    }

//    public void testEnv() {
//        System.out.println("✅ Loaded Email: " + dotenv.get("SMTP_USERNAME"));
//        System.out.println("✅ Loaded Password: " + dotenv.get("SMTP_PASSWORD"));
//    }

    public void sendEmail(String to, String subject, String message, String replyTo) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(message, true);
            helper.setFrom(senderEmail);
            helper.setReplyTo(replyTo);

            mailSender.send(mimeMessage);
            System.out.println("✅ Email sent successfully!");
        } catch (Exception e) {
            System.err.println("❌ Failed to send email: " + e.getMessage());
        }
    }

    // ✅ Method to send an email with an attachment


    public void sendEmailWithAttachment(String to, String subject, String message, String attachmentPath, String replyTo) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(message, true);
            helper.setFrom(senderEmail); // ✅ Uses the email from application.properties
            helper.setReplyTo(replyTo);  // ✅ Allows recipient to reply to the registered user

            // ✅ Add attachment
            File attachment = new File(attachmentPath);
            if (!attachment.exists()) {
                throw new IllegalArgumentException("Attachment file not found: " + attachmentPath);
            }
            FileSystemResource file = new FileSystemResource(attachment);
            helper.addAttachment(file.getFilename(), file);

            mailSender.send(mimeMessage);
            System.out.println("✅ Email with attachment sent successfully!");
        } catch (Exception e) {
            System.err.println("❌ Failed to send email with attachment: " + e.getMessage());
        }
    }

}
