package ru.bbnshp.controllers;

import ru.bbnshp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class AppController {

    @Autowired
    private final UserRepository users;

    public AppController(UserRepository users) {
        this.users = users;
    }

}
