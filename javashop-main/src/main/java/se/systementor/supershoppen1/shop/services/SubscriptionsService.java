package se.systementor.supershoppen1.shop.services;

import org.springframework.stereotype.Service;
import se.systementor.supershoppen1.shop.model.Category;
import se.systementor.supershoppen1.shop.model.CategoryRepository;
import se.systementor.supershoppen1.shop.model.Subscriptions;
import se.systementor.supershoppen1.shop.model.SubscriptionsRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionsService {

    private final SubscriptionsRepository repository;

    SubscriptionsService(SubscriptionsRepository rep) {
        super();
        this.repository = rep;
    }

    public List<String> getSignedUp() {
        ArrayList<String> l = new ArrayList<>();
        for(Subscriptions r : repository.findAll())
        {
            if(r.isActive()) l.add(r.getEmail());
        }
        return l;
    }

    public List<String> getAll(){
        ArrayList<String> l = new ArrayList<>();
        for(Subscriptions r : repository.findAll())
        {
            l.add(r.getEmail());
        }
        return l;
    }

   public Subscriptions add(String email){
        Subscriptions s = new Subscriptions();
        s.setEmail(email);
        repository.save(s);
        return s;
   }
}

