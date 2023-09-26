package org.example.controllers;

import org.example.entities.User;
import org.example.entities.UserRole;
import org.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


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
        //Test add(success)
//        User user = new User();
//        user.setLogin("VZone");
//        user.setPassword("123");
//        user.setRole(UserRole.USER);
//        users.save(user);
        //Show all user(only default admin)
        List<User> userList = users.findAll();
        for(User u: userList){
            System.out.println(u.getId());
            System.out.println(u.getLogin());
            System.out.println(u.getPassword());
            System.out.println(u.getRole().name());
        }

        return "main";
    }
}
