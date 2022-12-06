package se.systementor.supershoppen1.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import se.systementor.supershoppen1.shop.model.Newsletter;
import se.systementor.supershoppen1.shop.services.NewsletterService;

@Controller
public class NewsletterController {
    private NewsletterService newsletterService;

    @Autowired
    public NewsletterController(NewsletterService newsletterService) {
        this.newsletterService = newsletterService;
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

    @GetMapping(path = "/admin/newsletter/editnewsletter/{id}")

    public String showNewsletterById(@PathVariable Integer id, Model model){
       model.addAttribute("newsletters", newsletterService.getById(id));
       return "editnewsletter";
    }

    /*@PutMapping(path = "/admin/newsletter/editnewsletter/{id}")
    public ModelAndView updateNewsletterById(@PathVariable Integer id){
        ModelAndView mav = new ModelAndView("editnewsletter");
        mav.addObject("editnewsletter", newsletterService.getById(id));
        return mav;
    }


    @GetMapping(path = "/admin/newsletter/editnewsletter/{id}")
    public ModelAndView showNewsletterById(@PathVariable Integer id){
        ModelAndView mav = new ModelAndView("editnewsletter");
        mav.addObject("editnewsletter", newsletterService.getById(id));
        return mav;
    }*/


}
