package ru.bbnshp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.bbnshp.dto.*;
import ru.bbnshp.entities.Basket;
import ru.bbnshp.entities.Shoe;
import ru.bbnshp.entities.User;
import ru.bbnshp.entities.UserRole;
import ru.bbnshp.repositories.BasketRepository;
import ru.bbnshp.repositories.BrandRepository;
import ru.bbnshp.repositories.ShoeRepository;
import ru.bbnshp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import ru.bbnshp.response.JwtResponse;
import ru.bbnshp.response.MessageResponse;
import ru.bbnshp.services.UserDetailsImpl;
import ru.bbnshp.utils.JwtUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;


@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final ShoeRepository shoeRepository;

    @Autowired
    private final BrandRepository brandRepository;

    @Autowired
    private final BasketRepository basketRepository;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final JwtUtils jwtUtils;
    private final Pattern emailPattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");

    public UserController(UserRepository userRepository,
                          ShoeRepository shoeRepository,
                          BrandRepository brandRepository,
                          BasketRepository basketRepository,
                          AuthenticationManager authenticationManager,
                          JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.shoeRepository = shoeRepository;
        this.brandRepository = brandRepository;
        this.basketRepository = basketRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserDto loginUser){
        if(loginUser.getPassword() == null || loginUser.getLogin() == null){
            return ResponseEntity.badRequest().body(new MessageResponse("All fields must be filled in"));
        }
        if(!userRepository.existsByLogin(loginUser.getLogin())){
            return ResponseEntity.badRequest().body(new MessageResponse("There is no user with this login"));
        }
        if(userRepository.findByLogin(loginUser.getLogin()).isPresent() &&
           !(new BCryptPasswordEncoder().matches(loginUser.getPassword(), userRepository.findByLogin(loginUser.getLogin()).get().getPassword()))){
            return ResponseEntity.badRequest().body(new MessageResponse("Invalid password"));
        }
        Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getLogin(), loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getLogin(), userDetails.getEmail()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserDto registerUser){
        if(registerUser.getPassword() == null || registerUser.getLogin() == null || registerUser.getEmail() == null){
            return ResponseEntity.badRequest().body(new MessageResponse("All fields must be filled in"));
        }
        if(registerUser.getLogin().length() < 4){
            return ResponseEntity.badRequest().body(new MessageResponse("Login length must be greater than 4"));
        }
        if(userRepository.existsByLogin(registerUser.getLogin())){
            return ResponseEntity.badRequest().body(new MessageResponse("User with this login already exists"));
        }
        if(registerUser.getPassword().length() < 5){
            return ResponseEntity.badRequest().body(new MessageResponse("Password length must be greater than 4"));
        }
        if(!emailPattern.matcher(registerUser.getEmail()).matches()){
            return ResponseEntity.badRequest().body(new MessageResponse("Invalid email format"));
        }
        User user = new User();
        user.setLogin(registerUser.getLogin());
        user.setPassword(new BCryptPasswordEncoder().encode(registerUser.getPassword()));
        user.setEmail(registerUser.getEmail());
        user.setRole(UserRole.USER);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("New user registered"));
    }

    @GetMapping("/getProducts")
    public ResponseEntity<?> getProducts(){
        List<Shoe> shoeList = shoeRepository.findAll();
        return ResponseEntity.ok(shoeList);
    }

    @PostMapping("/getProduct")
    public ResponseEntity<?> getProduct(@RequestBody ShoeIdDto shoeDto){
        if(shoeDto.getId() == null){
            return ResponseEntity.badRequest().body(new MessageResponse("Shoe id must not be null"));
        }
        Optional<Shoe> shoeOptional = shoeRepository.findById(shoeDto.getId());
        if(shoeOptional.isPresent()){
            Shoe shoe = shoeOptional.get();
            return ResponseEntity.ok(shoe);
        }
        else{
            return ResponseEntity.badRequest().body(new MessageResponse("Shoe with this id doesn't exist"));
        }
    }

    @PostMapping("/getFilteredProducts")
    public ResponseEntity<?> getFilteredProduct(@RequestBody FilterDto filter){
        List<String> colorList = filter.getColors();
        if(colorList != null){
            List<Shoe> shoeList = shoeRepository.findByColorIn(colorList);
            return ResponseEntity.ok(shoeList);
        }
        else return ResponseEntity.badRequest().body(new MessageResponse("Color list is null"));
    }

    @PostMapping("/basketAdd")
    public ResponseEntity<?> basketAdd(@RequestBody ShoeToBasketDto dto){
        if(!shoeRepository.existsById(dto.getShoeId())){
            return ResponseEntity.badRequest().body(new MessageResponse("Shoe with this id doesn't exist"));
        }
        if(!userRepository.existsById(dto.getUserId())){
            return ResponseEntity.badRequest().body(new MessageResponse("User with this id doesn't exist"));
        }
        User user = userRepository.getReferenceById(dto.getUserId());
        Shoe shoe = shoeRepository.getReferenceById(dto.getShoeId());
        Basket basket = new Basket();
        basket.setShoe(shoe);
        user.addBasket(basket);
        userRepository.save(user);
        return ResponseEntity.ok(shoe);
    }

    @PostMapping("/getBasket")
    public ResponseEntity<?> getBasket(@RequestBody UserIdDto userId){
        if(!userRepository.existsById(userId.getUserId())){
            return ResponseEntity.badRequest().body(new MessageResponse("User with this id doesn't exist"));
        }
        List<Basket> basketList = basketRepository.findByUserId(userId.getUserId());
        List<Shoe> shoeList = new ArrayList<>();
        for(Basket basket: basketList){
            shoeList.add(basket.getShoe());
        }
        return ResponseEntity.ok(shoeList);
    }
}
