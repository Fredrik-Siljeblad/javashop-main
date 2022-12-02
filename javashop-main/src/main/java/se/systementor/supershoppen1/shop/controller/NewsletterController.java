package se.systementor.supershoppen1.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.systementor.supershoppen1.shop.services.NewsletterService;
import se.systementor.supershoppen1.shop.services.ProductService;

@Controller
public class NewsletterController {
    private NewsletterService newsletterService;

    @Autowired
    public NewsletterController(NewsletterService newsletterService) {
        this.newsletterService = newsletterService;
    }

    @GetMapping(path = "/newsletter")
    String empty(Model model)
    {
        return "newsletter";
    }
}
