
package se.systementor.supershoppen1.shop.services.EMailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import se.systementor.supershoppen1.shop.model.Email;

import java.util.Properties;

@Service
public class EmailServiceImp {

        public JavaMailSender getJavaMailSender() {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost("smtp.ethereal.email");
            mailSender.setPort(587);

            mailSender.setUsername("assunta.kihn15@ethereal.email");
            mailSender.setPassword("nxXrHM8HUduU9XaxWv");

            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.debug", "true");

            return mailSender;
        }

        public void sendSimpleMessage(Email mailObj) {

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(mailObj.getEmail());
            message.setTo("lukas.lundblad@live.se");
            message.setSubject(mailObj.getEmailSubject());
            message.setText(mailObj.getEmailContent());
            message.setReplyTo(mailObj.getEmailName());
            getJavaMailSender().send(message);
        }



}

