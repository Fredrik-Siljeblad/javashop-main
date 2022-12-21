package se.systementor.supershoppen1.shop.services.EMailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailServiceImp {
        @Autowired
        private JavaMailSender emailSender;
        public void sendSimpleMessage(
                String to, String subject, String text) {

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("noreply@systementor.se");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);

        }

}
