package at.codefabrik.registration.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@AllArgsConstructor
public class MainController {


    public String showHomePage(Model model){

        return "index";
    }
}
