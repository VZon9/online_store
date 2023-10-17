package ru.bbnshp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bbnshp.repositories.UserRepository;

@RestController
@CrossOrigin
public class TestController {

    @Autowired
    private final UserRepository users;

    @Autowired
    private final AuthenticationManager authenticationManager;

    public TestController(UserRepository users, AuthenticationManager authenticationManager) {
        this.users = users;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/test")
    public ResponseEntity<?> login(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication instanceof AnonymousAuthenticationToken){
            return ResponseEntity.ok("NotAuth");
        }
        return ResponseEntity.ok("Auth");
    }
}
