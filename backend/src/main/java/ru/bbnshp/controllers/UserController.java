package ru.bbnshp.controllers;

import ch.qos.logback.core.boolex.Matcher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.bbnshp.dto.LoginUserDto;
import ru.bbnshp.dto.RegisterUserDto;
import ru.bbnshp.entities.User;
import ru.bbnshp.entities.UserRole;
import ru.bbnshp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import ru.bbnshp.response.JwtResponse;
import ru.bbnshp.response.MassageResponse;
import ru.bbnshp.services.UserDetailsImpl;
import ru.bbnshp.utils.JwtUtils;

import java.beans.Encoder;
import java.util.regex.Pattern;


@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private final UserRepository users;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final JwtUtils jwtUtils;
    private final Pattern emailPattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");

    public UserController(UserRepository users, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.users = users;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserDto loginUser){
        if(loginUser.getPassword() == null || loginUser.getLogin() == null){
            return ResponseEntity.badRequest().body(new MassageResponse("All fields must be filled in"));
        }
        if(!users.existsByLogin(loginUser.getLogin())){
            return ResponseEntity.badRequest().body(new MassageResponse("There is no user with this login"));
        }
        if(users.findByLogin(loginUser.getLogin()).isPresent() &&
           !(new BCryptPasswordEncoder().matches(loginUser.getPassword(), users.findByLogin(loginUser.getLogin()).get().getPassword()))){
            return ResponseEntity.badRequest().body(new MassageResponse("Invalid password"));
        }
        Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getLogin(), loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getLogin()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserDto registerUser){
        if(registerUser.getPassword() == null || registerUser.getLogin() == null || registerUser.getEmail() == null){
            return ResponseEntity.badRequest().body(new MassageResponse("All fields must be filled in"));
        }
        if(registerUser.getLogin().length() < 4){
            return ResponseEntity.badRequest().body(new MassageResponse("Login length must be greater than 4"));
        }
        if(users.existsByLogin(registerUser.getLogin())){
            return ResponseEntity.badRequest().body(new MassageResponse("User with this login already exists"));
        }
        if(registerUser.getPassword().length() < 5){
            return ResponseEntity.badRequest().body(new MassageResponse("Password length must be greater than 4"));
        }
        if(!emailPattern.matcher(registerUser.getEmail()).matches()){
            return ResponseEntity.badRequest().body(new MassageResponse("Invalid email format"));
        }
        User user = new User();
        user.setLogin(registerUser.getLogin());
        user.setPassword(new BCryptPasswordEncoder().encode(registerUser.getPassword()));
        user.setEmail(registerUser.getEmail());
        user.setRole(UserRole.USER);
        users.save(user);
        return ResponseEntity.ok(new MassageResponse("New user registered"));
    }
}
