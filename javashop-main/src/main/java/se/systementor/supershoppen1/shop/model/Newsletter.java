package se.systementor.supershoppen1.shop.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name="NEWSLETTER")

public class Newsletter {
    private String greeting;
    private String body;
    private LocalDateTime sentDate;

    @ElementCollection
    private List<String> received; //List all email addresses that have received the letter.

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    public Newsletter() {

    }

    public Integer getId() {
        return id;
      }
    
      public void setId(Integer id) {
        this.id = id;
      }

      public void setGreeting(String g)
      {
          greeting = g;
      }
  

      public String getGreeting()
      {
          return greeting;
      }


    public void setBody(String string) {
        body = string;
    }

    public String getBody() {
        return body;
    }

    public LocalDateTime getSentDate() {
        return sentDate;
    }

    public void setSentDate(LocalDateTime sentDate) {
        this.sentDate = sentDate;
    }

    public void setReceived(List<String> signedUp) {
    }
}

