package se.systementor.supershoppen1.shop.services;

import org.springframework.stereotype.Service;
import se.systementor.supershoppen1.shop.model.NewsletterRepository;

@Service
public class NewsletterService {

    private final NewsletterRepository repository;

    NewsletterService(NewsletterRepository rep) {
        super();
        this.repository = rep;
    }


}
