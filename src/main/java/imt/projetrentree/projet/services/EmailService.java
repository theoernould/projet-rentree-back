package imt.projetrentree.projet.services;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {
//    private JavaMailSender javaMailSender;
//
//    private Map<String,String> emailTokens = new HashMap<>();
//
//    public void sendPasswordResetEmail(String recipientEmail, String resetToken) {
//        // Create and configure an email message
//        SimpleMailMessage mailMessage = new
//                SimpleMailMessage();
//        mailMessage.setTo(recipientEmail);
//        mailMessage.setSubject("Password Reset Request");
//        mailMessage.setText("Click the following link to reset your password: " + resetToken);
//
//        // Send the email
//        javaMailSender.send(mailMessage);
//    }
}
