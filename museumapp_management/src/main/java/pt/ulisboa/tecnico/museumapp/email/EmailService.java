package pt.ulisboa.tecnico.museumapp.email;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Component
@NoArgsConstructor
public class EmailService {
    private final static String EMAIL_CONFIRMATION_SUBJECT = "Confirm your udeesa account";

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendConfirmationEmail(String token, String email) {
        // build email
        // send message
        String message = "Welcome to Udeesa, test token" + token;
        String from = "no-reply@udeesa.org";
        send(email, from, message);
    }

    @Async
    public void send(String to, String from, String email) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(EMAIL_CONFIRMATION_SUBJECT);
            helper.setText(email);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new IllegalStateException("failed to send email");
        }
    }
    public void sendEmailWithAttachment(String to, String from, String email, Integer visit_id) throws MessagingException, IOException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        String filePath = "./src/main/resources/static/img/QRCode" + visit_id + ".png";
        String fileName = "QRCode of visit" + visit_id;

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        String subject = "Your ticket to Museu da computação";
        helper.setTo(to);
        helper.setFrom(from);
        helper.setSubject(subject);
        helper.setText(email);

        // hard coded a file path
        // FileSystemResource file = new FileSystemResource(new    File("path/img.png"));
        // helper.addAttachment("Google Photo",file);
        helper.addAttachment(fileName, new ClassPathResource(filePath));
        javaMailSender.send(msg);
    }
}