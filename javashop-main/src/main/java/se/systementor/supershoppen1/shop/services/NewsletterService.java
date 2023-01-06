package se.systementor.supershoppen1.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.systementor.supershoppen1.shop.model.Newsletter;
import se.systementor.supershoppen1.shop.model.NewsletterRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NewsletterService {

    private final NewsletterRepository newsletterRepository;
    private final SubscriptionsService subscriptionsService;

    public NewsletterService(NewsletterRepository newsletterRepository, SubscriptionsService subscriptionsService) {
        super();
        this.newsletterRepository = newsletterRepository;
        this.subscriptionsService = subscriptionsService;    }

    public List<Newsletter> getAll(){
        var l = new ArrayList<Newsletter>();
        for(Newsletter r : newsletterRepository.findAll())
        {
            l.add(r);
        }
        return l;
    }

    public List<Newsletter> getSent(){
        var l = new ArrayList<Newsletter>();
        for(Newsletter r : newsletterRepository.findAll())
        {
            if(r.getSentDate() != null) l.add(r);
        }
        return l;
    }

    public Newsletter getById(Integer id){
        Optional<Newsletter> nl = newsletterRepository.findById(id);
        if(nl.isEmpty()) {
            return null;
        }else{
            return nl.get();
        }

    }

    public String send(Integer id){
        Newsletter nl = getById(id);
        if(nl.getSentDate() != null){
            return "Newsletter was already sent " + nl.getSentDate().toString() +".";
        }else{
            nl.setSentDate(LocalDateTime.now());
            nl.setReceived(subscriptionsService.getSignedUp());
            //TODO: Add actual functionality for sending out mail here!
            return "Sending Newsletter out. " + nl.getSentDate().toString() +".";
        }

    }
    public Newsletter create(Newsletter newNewsletter) {
        return newsletterRepository.save(newNewsletter);
    }
}

