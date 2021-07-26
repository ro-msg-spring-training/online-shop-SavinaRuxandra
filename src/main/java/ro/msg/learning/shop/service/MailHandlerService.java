package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.service.exception.MailHandlerException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class MailHandlerService {

    @Value("${spring.mail.from}")
    private String from;

    @Value("${spring.mail.subject}")
    private String subject;

    @Value("${spring.mail.bodyPath}")
    private String bodyPath;

    private final JavaMailSender emailSender;

    public void sendOrderConfirmationMail(Order order) {
        sendMail(order.getCustomer().getEmailAddress(), bodyParser(order));
    }

    public void sendMail(String to, String body) {
        try{
            String extension = bodyPath.split("\\.")[1];

            if(extension.equals("txt"))
                sendMailPlain(to, body);
            else if(extension.equals("html"))
                sendMailHtml(to, body);
            else
                throw new MailHandlerException("Body extension not accepted");
        }
        catch (IndexOutOfBoundsException exception) {
            throw new MailHandlerException("Invalid format for body path");
        }
        catch (MailHandlerException exception) {
            throw new MailHandlerException(exception.getMessage());
        }
    }

    public void sendMailPlain(String to, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        emailSender.send(message);
    }

    public void sendMailHtml(String to, String body) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
            emailSender.send(message);
        } catch (MailException | MessagingException e) {
            throw new MailHandlerException("Could not send confirmation email");
        }
    }

    public String bodyParser(Object object) {
        try {
            Path file = Path.of(new ClassPathResource(bodyPath).getFile().getPath());
            String bodyText = Files.readString(file);
            ExpressionParser parser = new SpelExpressionParser();
            Expression expression = parser.parseExpression(bodyText, new TemplateParserContext());
            StandardEvaluationContext context = new StandardEvaluationContext(object);
            return (String) expression.getValue(context);
        } catch (IOException e) {
            throw new MailHandlerException("Could not parse mail body");
        }
    }

}
