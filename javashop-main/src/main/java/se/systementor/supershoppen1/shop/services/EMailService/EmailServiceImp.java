
package se.systementor.supershoppen1.shop.services.EMailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import se.systementor.supershoppen1.shop.model.Email;

public class EmailServiceImp {
        @Autowired
        private JavaMailSender emailSender;


        public void sendSimpleMessage(Email mailObj) {

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(mailObj.getEmail());
            message.setTo("noreply@systementor.se");
            message.setSubject(mailObj.getEmailContent());
            message.setText(mailObj.getEmailContent());
            emailSender.send(message);

        }

}

