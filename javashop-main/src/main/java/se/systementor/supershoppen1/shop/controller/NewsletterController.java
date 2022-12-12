package se.systementor.supershoppen1.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import se.systementor.supershoppen1.shop.model.Newsletter;
import se.systementor.supershoppen1.shop.model.Subscription;
import se.systementor.supershoppen1.shop.services.NewsletterService;
import se.systementor.supershoppen1.shop.services.SubscriptionsService;

import java.util.List;

@Controller
public class NewsletterController {
    private NewsletterService newsletterService;
    private SubscriptionsService subscriptionsService;

    @Autowired
    public NewsletterController(NewsletterService newsletterService, SubscriptionsService subscriptionsService) {
        this.newsletterService = newsletterService;
        this.subscriptionsService = subscriptionsService;
    }

    // Endpoint to create a newsletter.
    @GetMapping(path = "/admin/createNewsletter")
    public String createNewsletter(Model model)
    {
        Newsletter newsletter = new Newsletter();
        model.addAttribute("newsletter", newsletter);

        return "createNewsletter";
    }

    // Create newsletter from /admin/newsletter submit btn.
    @PostMapping(path = "/admin/newsletter")
    public String submitForm(@ModelAttribute("newsletter") Newsletter newsletters){
        newsletterService.create(newsletters);
        return "redirect:/admin/newsletter";
    }

    // The main newsletter menu.
    @GetMapping(path = "/admin/newsletter")
    public ModelAndView showAll(){
        ModelAndView mav = new ModelAndView("newsletter");
        mav.addObject("newsletters", newsletterService.getAll());
        return mav;
    }

    @GetMapping(path = "/admin/newsletter/edit/{id}")
    public String showNewsletterById(@PathVariable Integer id, Model model){
       model.addAttribute("newsletter", newsletterService.getById(id));
       return "editnewsletter";
    }

    @PostMapping(path = "/admin/newsletter/{id}")
    public String editNewsletterById(@PathVariable Integer id, @ModelAttribute("newsletter") Newsletter newsletter){
        Newsletter existingNewsletter = newsletterService.getById(id);
        existingNewsletter.setId(id);
        existingNewsletter.setGreeting(newsletter.getGreeting());
        existingNewsletter.setBody(newsletter.getBody());

        newsletterService.create(existingNewsletter);
        return "redirect:/admin/newsletter";
    }


    // Visa sent news letter sidan beroende på vilket newsletter man har valt.
    /*@GetMapping(path= "/admin/sentnewsletters/{id}")
    public String showSentNewslettersById(@PathVariable Integer id, Model model){
        //model.addAttribute("newsletters", newsletterService.getById(id));
        System.out.println("newsletter");
        model.addAttribute("subscriptionList", subscriptionsService.getAll());

        System.out.println("sent newsletters");
        return "sentnewsletters";
    }*/

    @GetMapping(path= "/admin/sentnewsletters/{id}")
    public String showSentNewslettersById(@PathVariable Integer id, Model model){
        model.addAttribute("newsletters", newsletterService.getById(id));
        System.out.println("newsletter");
        model.addAttribute("subscriptionList", subscriptionsService.getSignedUp());

        System.out.println("sent newsletters");
        return "sentnewsletters";
    }


}
