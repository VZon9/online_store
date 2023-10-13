package ru.bbnshp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.bbnshp.dto.LoginUserDto;
import ru.bbnshp.dto.RegisterUserDto;
import ru.bbnshp.entities.User;
import ru.bbnshp.entities.UserRole;
import ru.bbnshp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import ru.bbnshp.response.JwtResponse;
import ru.bbnshp.services.UserDetailsImpl;
import ru.bbnshp.services.UserDetailsServiceImpl;
import ru.bbnshp.utils.JwtUtils;


@RestController
@CrossOrigin
public class AppController {

    @Autowired
    private final UserRepository users;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final JwtUtils jwtUtils;

    public AppController(UserRepository users, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.users = users;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserDto user){
        Authentication authentication =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getLogin()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserDto registerUser){
        User user = new User();
        user.setLogin(registerUser.getLogin());
        user.setPassword(new BCryptPasswordEncoder().encode(registerUser.getPassword()));
        user.setEmail(registerUser.getEmail());
        user.setRole(UserRole.USER);
        users.save(user);
        return ResponseEntity.ok("New user registered");
    }


}
