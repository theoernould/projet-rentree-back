package imt.projetrentree.projet.services;

import imt.projetrentree.projet.exceptions.user.CouldNotSendMailException;
import imt.projetrentree.projet.models.User;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService {

    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;

    public EmailService(JavaMailSender emailSender, TemplateEngine templateEngine) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    private void sendMail(String to, String subject, String templateName, Context context) {
        try {
            MimeMessage message = emailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);

            String emailContent = templateEngine.process(templateName, context);

            helper.setText(emailContent, true);

            helper.setFrom("n79880215@gmail.com","Projet Rentrée no reply");

            emailSender.send(message);
        } catch (Exception e) {
            throw new CouldNotSendMailException(to);
        }
    }

    public void sendChangePasswordEmail(String token, String email) {
        String urlWithToken = "https://localhost:19006?token=" + token;

        Context context = new Context();
        context.setVariable("url", urlWithToken);

        sendMail(email, "It seems you forgot your password. Click here to reset it.", "resetPassword", context);
    }

    public void sendWelcomeEmail(User user) {
        Context context = new Context();
        context.setVariable("firstname", user.getFirstname());
        context.setVariable("lastname", user.getLastname());

        sendMail(user.getEmail(), "Welcome to Projet Rentrée", "welcome", context);
    }
}