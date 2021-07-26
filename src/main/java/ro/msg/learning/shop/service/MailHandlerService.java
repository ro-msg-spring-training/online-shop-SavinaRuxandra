package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
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

    @Value("${spring.mail.textType}")
    private String textType;

    private final JavaMailSender emailSender;

    public void sendOrderConfirmationMail(Order order) {
        sendMail(order.getCustomer().getEmailAddress(), subject, bodyParser(order));
    }

    public void sendMail(String to, String subject, String body) {
        if(textType.equals("text"))
            sendMailPlain(to, subject, body);
        else if(textType.equals("html"))
            sendMailHtml(to, subject, body);
        else
            throw new MailHandlerException("Wrong type for body");
    }

    public void sendMailPlain(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        emailSender.send(message);
    }

    public void sendMailHtml(String to, String subject, String body) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
            emailSender.send(message);
        } catch (MessagingException e) {
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
