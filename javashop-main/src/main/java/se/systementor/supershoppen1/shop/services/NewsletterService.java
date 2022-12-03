package se.systementor.supershoppen1.shop.services;

import org.springframework.stereotype.Service;
import se.systementor.supershoppen1.shop.model.Newsletter;
import se.systementor.supershoppen1.shop.model.NewsletterRepository;
import se.systementor.supershoppen1.shop.model.UserAccountRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NewsletterService {

    private final NewsletterRepository newsletterRepository;
    private final UserAccountRepository userAccountRepository;
    private final SubscriptionsService subscriptionsService;

    NewsletterService(NewsletterRepository newsletterRepository, UserAccountRepository userAccountRepository, SubscriptionsService subscriptionsService) {
        super();
        this.newsletterRepository = newsletterRepository;
        this.userAccountRepository = userAccountRepository;
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
        List<String> recipients;

        recipients = subscriptionsService.getSignedUp();


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

