package pt.ulisboa.tecnico.museumapp.email;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;

@Component
@NoArgsConstructor
public class EmailService {
    private final static String EMAIL_CONFIRMATION_SUBJECT = "O seu bilhete para o museu da computação";

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendConfirmationEmail(String token, String email) {
        // build email
        // send message
        String message = "O seu bilhete para o museu da computação" + token;
        String from = "no-reply@udeesa.org";
        send(email, from, message);
    }

    @Async
    public void send(String to, String from, String content) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(EMAIL_CONFIRMATION_SUBJECT);
            helper.setText(content);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new IllegalStateException("failed to send email");
        }
    }
    public void sendEmailWithAttachment(String to, String from, String content, Integer visit_id) throws MessagingException, IOException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        String filePath = "/Users/carolina/Desktop/Tese/QRCodes/Quote.png";
        String fileName = "QRCode of visit" + visit_id;

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        String subject = "Your ticket to Museu da computação";
        helper.setTo(to);
        helper.setFrom(from);
        helper.setSubject(subject);
        helper.setText(content);

        // hard coded a file path
        // FileSystemResource file = new FileSystemResource(new    File("path/img.png"));
        // helper.addAttachment("Google Photo",file);
        helper.addAttachment(fileName, new ClassPathResource(filePath));
        javaMailSender.send(msg);

        System.out.println("message sent....");
    }
}