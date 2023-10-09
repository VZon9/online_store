package org.example.controllers;
import org.example.dto.UserDto;
import org.example.entities.User;
import org.example.entities.UserRole;
import org.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class AppController {

    @Autowired
    private final UserRepository users;

    public AppController(UserRepository users) {
        this.users = users;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto regUser){
        User newUser = new User();
        newUser.setRole(UserRole.USER);
        newUser.setLogin(regUser.getLogin());
        newUser.setName(regUser.getName());
        newUser.setEmail(regUser.getEmail());
        users.save(newUser);
        return ResponseEntity.ok(regUser);
    }
}
