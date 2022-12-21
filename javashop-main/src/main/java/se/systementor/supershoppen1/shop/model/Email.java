package se.systementor.supershoppen1.shop.model;

public class Email {
    public String senderName;

    public String getSenderName() {
        return senderName;
    }

    public String text;
    public String to;
    public String subject;


    public String getText() {
        return text;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }
}
