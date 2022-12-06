package se.systementor.supershoppen1.shop.model;

import javax.persistence.*;


@Entity
@Table(name="SUBSCRIPTIONS")
public class Subscription {


    private String email;

    boolean active;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    public Subscription(String email, boolean active) {
    }

    public Subscription() {

    }

    public Integer getId() {
        return id;
      }
    
      public void setId(Integer id) {
        this.id = id;
      }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

