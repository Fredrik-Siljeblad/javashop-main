
package se.systementor.supershoppen1.shop.services.EMailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import se.systementor.supershoppen1.shop.model.Email;

@Service
public class EmailServiceImp {
        @Autowired
        private JavaMailSender emailSender;


        public void sendSimpleMessage(Email mailObj) {

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(mailObj.getEmail());
            message.setTo("lukas.lundblad@live.se");
            message.setSubject(mailObj.getEmailSubject());
            message.setText(mailObj.getEmailContent());
            message.setReplyTo(mailObj.getEmailName());
            emailSender.send(message);
            System.out.println("email sent ? ");

        }

}

