package se.systementor.supershoppen1.shop.services;

import org.springframework.stereotype.Service;
import se.systementor.supershoppen1.shop.model.Product;
import se.systementor.supershoppen1.shop.model.Subscription;
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
        for(Subscription r : repository.findAll())
        {
            if(r.isActive()) l.add(r.getEmail());
        }
        return l;
    }

    public List<String> getAll(){
        ArrayList<String> l = new ArrayList<>();
        for(Subscription r : repository.findAll())
        {
            l.add(r.getEmail());
        }
        return l;
    }

   public Subscription add(String email){
        Subscription s = new Subscription(email, true);
        repository.save(s);
        return s;
   }

    public void save(Subscription newSub) {
            repository.save(newSub);
    }

}

