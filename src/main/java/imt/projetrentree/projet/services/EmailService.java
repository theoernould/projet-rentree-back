package imt.projetrentree.projet.services;

import imt.projetrentree.projet.dto.order.OrderSummaryDTO;
import imt.projetrentree.projet.exceptions.user.CouldNotSendMailException;
import imt.projetrentree.projet.models.User;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import static imt.projetrentree.projet.ProjetApplication.APP_NAME;

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

            helper.setFrom("n79880215@gmail.com", APP_NAME + " |  no-reply");

            emailSender.send(message);
        } catch (Exception e) {
            throw new CouldNotSendMailException(to);
        }
    }

    @Async
    public void sendChangePasswordEmail(String token, String email) {
        String urlWithToken = "http://localhost:19006/ChangePassword?token=" + token;

        Context context = new Context();
        context.setVariable("url", urlWithToken);

        sendMail(email, "On dirait que vous avec oublié votre mot de passe. Pas de soucis, cliquez ici pour le réinitialiser.", "resetPassword", context);
    }

    @Async
    public void sendPasswordChangeConfirmationEmail(User user) {
        Context context = new Context();
        context.setVariable("firstname", user.getFirstname());
        context.setVariable("lastname", user.getLastname());

        sendMail(user.getEmail(), "Confirmation de changement de mot de passe", "passwordChangeConfirmation", context);
    }

    @Async
    public void sendWelcomeEmail(User user) {
        Context context = new Context();
        context.setVariable("firstname", user.getFirstname());
        context.setVariable("lastname", user.getLastname());

        sendMail(user.getEmail(), "Bienvenue sur " + APP_NAME + " !", "welcome", context);
    }

    @Async
    public void sendOrderSummaryEmail(OrderSummaryDTO orderSummaryDTO) {
        Context context = new Context();
        context.setVariable("user", orderSummaryDTO.getUser());
        context.setVariable("dishesWithQuantities", orderSummaryDTO.getDishesWithQuantities());
        context.setVariable("orderTotalPrice", orderSummaryDTO.getTotalPrice());
        context.setVariable("address", orderSummaryDTO.getAddress());

        sendMail(orderSummaryDTO.getUser().getEmail(), "Résumé de votre commande", "orderConfirmation", context);
    }

}