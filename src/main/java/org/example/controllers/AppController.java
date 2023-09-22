package org.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/")
    String getRedirect(){
        return "redirect:/main";
    }

    @GetMapping("/main")
    String getMain(Model model){
        return "main";
    }
}
