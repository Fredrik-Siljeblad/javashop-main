package se.systementor.supershoppen1.shop.model;

import javax.persistence.Entity;


public class Email {

    private String emailName; // Senders name
    private String email; // Senders email
    private String emailSubject; // Subject / ärende
    private String emailContent; // Body / Innehåll

    public Email() {

    }

    public String getEmailName() {
        return emailName;
    }

    public void setEmailName(String emailName) {
        this.emailName = emailName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent;
    }
}
