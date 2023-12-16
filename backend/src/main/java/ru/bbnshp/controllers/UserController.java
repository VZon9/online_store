package ru.bbnshp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.bbnshp.entities.*;
import ru.bbnshp.mapper.Mapper;
import ru.bbnshp.repositories.*;
import ru.bbnshp.request.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.bbnshp.response.JwtResponse;
import ru.bbnshp.response.MessageResponse;
import ru.bbnshp.services.UserDetailsImpl;
import ru.bbnshp.utils.JwtUtils;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final ShoeRepository shoeRepository;

    @Autowired
    private final BasketRepository basketRepository;

    @Autowired
    private final ShoeSizeRepository shoeSizeRepository;

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final JwtUtils jwtUtils;
    private final Pattern emailPattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");

    public UserController(UserRepository userRepository,
                          ShoeRepository shoeRepository,
                          BasketRepository basketRepository,
                          ShoeSizeRepository shoeSizeRepository,
                          AuthenticationManager authenticationManager,
                          OrderRepository orderRepository, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.shoeRepository = shoeRepository;
        this.basketRepository = basketRepository;
        this.shoeSizeRepository = shoeSizeRepository;
        this.authenticationManager = authenticationManager;
        this.orderRepository = orderRepository;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserRequest loginUser){
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
    public ResponseEntity<?> register(@RequestBody RegisterUserRequest registerUser){
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
        return ResponseEntity.ok(shoeList.stream().map(Mapper::toShoeDto).collect(Collectors.toList()));
    }

    @PostMapping("/getProduct")
    public ResponseEntity<?> getProduct(@RequestBody ShoeIdRequest request){
        if(request.getId() == null){
            return ResponseEntity.badRequest().body(new MessageResponse("Shoe id must not be null"));
        }
        Optional<Shoe> shoeOptional = shoeRepository.findById(request.getId());
        if(shoeOptional.isPresent()){
            Shoe shoe = shoeOptional.get();
            return ResponseEntity.ok(Mapper.toShoeDto(shoe));
        }
        else{
            return ResponseEntity.badRequest().body(new MessageResponse("Shoe with this id doesn't exist"));
        }
    }

    @PostMapping("/getFilteredProducts")
    public ResponseEntity<?> getFilteredProduct(@RequestBody FilterRequest filter){
        List<String> colorList = filter.getColors();
        if(colorList != null){
            List<Shoe> shoeList = shoeRepository.findByColorIn(colorList);
            return ResponseEntity.ok(shoeList.stream().map(Mapper::toShoeDto).collect(Collectors.toList()));
        }
        else return ResponseEntity.badRequest().body(new MessageResponse("Color list is null"));
    }

    @PostMapping("/basketAdd")
    public ResponseEntity<?> basketAdd(@RequestBody ShoeToBasketRequest request){
        if(!shoeRepository.existsById(request.getShoeId())){
            return ResponseEntity.badRequest().body(new MessageResponse("Shoe with this id doesn't exist"));
        }
        if(!userRepository.existsById(request.getUserId())){
            return ResponseEntity.badRequest().body(new MessageResponse("User with this id doesn't exist"));
        }
        if(request.getSizeId() == 0){
            return ResponseEntity.badRequest().body(new MessageResponse("Size didn't choose"));
        }
        if(shoeSizeRepository.findById(request.getSizeId()).get().getExistingNum() == 0){
            return ResponseEntity.badRequest().body(new MessageResponse("The size is not available"));
        }
        Optional<Basket> basketOptional = basketRepository.findByUserIdAndShoeIdAndSizeValue(
                request.getUserId(),
                request.getShoeId(),
                shoeSizeRepository.findById(request.getSizeId()).get().getSize().getValue());
        if(basketOptional.isPresent()){
            Basket basket = basketOptional.get();
            basket.setNum(basket.getNum() + 1);
            basketRepository.save(basket);
        }
        else {
            User user = userRepository.getReferenceById(request.getUserId());
            Shoe shoe = shoeRepository.getReferenceById(request.getShoeId());
            ShoeSize size = shoeSizeRepository.getReferenceById(request.getSizeId());
            Basket basket = new Basket();
            basket.setShoe(shoe);
            basket.setSize(size.getSize());
            basket.setNum(1);
            user.addBasket(basket);
            userRepository.save(user);
        }
        return ResponseEntity.ok(new MessageResponse("Shoe added to basket"));
    }

    @PostMapping("/getBasket")
    public ResponseEntity<?> getBasket(@RequestBody UserIdRequest userId){
        if(!userRepository.existsById(userId.getUserId())){
            return ResponseEntity.badRequest().body(new MessageResponse("User with this id doesn't exist"));
        }
        List<Basket> basketList = basketRepository.findByUserId(userId.getUserId());
        return ResponseEntity.ok(basketList.stream().map(Mapper::toBasketDto).toList());
    }

    @PostMapping("/removeFromBasket")
    public ResponseEntity<?> removeBasket(@RequestBody BasketRequest request){
        if(!basketRepository.existsById(request.getBasketId())){
            return ResponseEntity.badRequest().body(new MessageResponse("Shoe with this id doesn't exist"));
        }
        if(!userRepository.existsById(request.getUserId())) {
            return ResponseEntity.badRequest().body(new MessageResponse("User with this id doesn't exist"));
        }
        basketRepository.deleteById(request.getBasketId());
        return ResponseEntity.ok(new MessageResponse("Shoe deleted from basket"));
    }

    @PostMapping("/sort")
    public ResponseEntity<?> sortShoesList(@RequestBody SortRequest sortRequest){
        List<Shoe> shoeList = shoeRepository.findAll();
        if(sortRequest.getSortType().equals("default")){
            shoeList.sort(Comparator.comparing(Shoe::getBoughtNum));

        }
        if(sortRequest.getSortType().equals("price")){
            shoeList.sort(Comparator.comparing(Shoe::getPrice));

        }
        if(sortRequest.getDirection().equals("up")){
            Collections.reverse(shoeList);
        }
        return ResponseEntity.ok(shoeList.stream().map(Mapper::toShoeDto).toList());
    }

    @PostMapping("/makeOrder")
    public ResponseEntity<?> makeOrder(@RequestBody BasketToOrderRequest request){
        Optional<User> userOptional = userRepository.findById(request.getUserId());
        if(userOptional.isEmpty()){
            return ResponseEntity.badRequest().body(new MessageResponse("There is no user with this id"));
        }
        if(request.getBasketsId().size() == 0){
            return ResponseEntity.badRequest().body(new MessageResponse("New order has been not created, because shoe set is null"));
        }
        User user = userOptional.get();
        Order order = new Order();
        for(Integer basketId: request.getBasketsId()){
            Optional<Basket> basketOptional = basketRepository.findById(basketId);
            if(basketOptional.isEmpty()){
                return ResponseEntity.badRequest().body(new MessageResponse("There is no basket with this id"));
            }
            Basket basket = basketOptional.get();
            OrderShoe orderShoe = new OrderShoe();
            orderShoe.setShoe(basket.getShoe());
            orderShoe.setSize(basket.getSize());
            orderShoe.setNum(basket.getNum());
            Optional<ShoeSize> shoeSizeOptional = shoeSizeRepository.findByShoeIdAndSizeValue(basket.getShoe().getId(), basket.getSize().getValue());
            if(shoeSizeOptional.isEmpty()){
                return ResponseEntity.badRequest().body(new MessageResponse("There is no size of this shoe"));
            }
            ShoeSize shoeSize = shoeSizeOptional.get();
            int existingNum = shoeSize.getExistingNum();
            shoeSize.setExistingNum(existingNum - basket.getNum());
            shoeSizeRepository.save(shoeSize);
            order.addOrder(orderShoe);
            basketRepository.delete(basket);
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zdt = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        order.setDate(new Date(zdt.toInstant().toEpochMilli()));
        order.setStatus(OrderStatus.ACCEPTED_FOR_PROCESSING);
        user.addOrder(order);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("New order has been created"));
    }

    @PostMapping("/getOrders")
    public ResponseEntity<?> getOrders(@RequestBody UserIdRequest request){
        if(!userRepository.existsById(request.getUserId())){
            return ResponseEntity.badRequest().body(new MessageResponse("User with this id doesn't exist"));
        }
        List<Order> basketList = orderRepository.findByUserId(request.getUserId());
        return ResponseEntity.ok(basketList.stream().map(Mapper::toOrderDto).toList());
    }

}
