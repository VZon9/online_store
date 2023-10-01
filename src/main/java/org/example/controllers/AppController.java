package org.example.controllers;
import org.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AppController {

    @Autowired
    UserRepository users;

    @GetMapping("/")
    String getRedirect(){
        return "redirect:/main";
    }

    @GetMapping("/main")
    String getMain(Model model){
        return "main";
    }
}
