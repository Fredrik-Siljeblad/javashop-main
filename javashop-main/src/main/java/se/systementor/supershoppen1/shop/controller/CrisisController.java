package se.systementor.supershoppen1.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.systementor.supershoppen1.shop.model.Crisis;
import se.systementor.supershoppen1.shop.model.utils.CrisisInfoUtil;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class CrisisController {

    CrisisInfoUtil crisisInfoUtil = new CrisisInfoUtil();
    private HomeController homeController;

    public CrisisController(HomeController homeController) {
        this.homeController = homeController;
    }

    @GetMapping("/crisis")
    String showCrisisInfo(Model model) throws IOException {
        this.homeController.hideSubscription(model);
        ArrayList<Crisis> last10crisis = crisisInfoUtil.getCrisisInfo();
        model.addAttribute("last10crisis", last10crisis);

        return "crisisInfo";

    }
}
