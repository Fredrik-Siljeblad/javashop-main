package se.systementor.supershoppen1.shop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Entity
public class Newsletter {
    private String greeting;
    private String body;

    private Date sentDate;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

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

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }
}

