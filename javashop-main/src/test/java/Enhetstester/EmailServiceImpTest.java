package Enhetstester;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import se.systementor.supershoppen1.shop.model.Email;
import se.systementor.supershoppen1.shop.services.EMailService.EmailServiceImp;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;



class EmailServiceImpTest {
   EmailServiceImp emailServiceImp;

   @BeforeEach
   void setUp(){
       emailServiceImp = new EmailServiceImp();
   }

    @Test
    void test_sendSimpleMessage(){
        // Arrange
        Email email = new Email();
        email.setEmail("william7e.se@gmail.com");
        email.setEmailContent("Hii");
        email.setEmailSubject("Subject");
        email.setEmailName("Lukas");

        //Act
        String result = emailServiceImp.sendSimpleMessage(email);

        //Assert
        assertEquals("Mail sending succeeded",result);

    }

}
