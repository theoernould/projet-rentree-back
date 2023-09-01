package imt.projetrentree.projet.services;

import imt.projetrentree.projet.exceptions.user.CouldNotSendMailException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendChangePasswordEmail(String token, String email) {
        try {
            MimeMessage message = emailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setSubject("It seems you forgot your password. Click here to reset it.");

            String urlWithToken = "https://localhost:19006?token=" + token;
            String emailContent = "<html><body>";
            emailContent += "<p>It seems like you've requested a password change <\\br> Click on the button below to reset your password</p>";
            emailContent += "<a href='" + urlWithToken + "'>Reset Password</a>";
            emailContent += "</body></html>";

            helper.setText(emailContent, true);

            helper.setFrom("n79880215@gmail.com","Projet Rentrée no reply");
            emailSender.send(message);
        } catch (Exception e) {
            throw new CouldNotSendMailException(email);
        }
    }
}