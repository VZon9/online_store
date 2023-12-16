package ru.bbnshp.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.bbnshp.dto.OrderDto;
import ru.bbnshp.dto.ShoeSizeDto;
import ru.bbnshp.entities.*;
import ru.bbnshp.mapper.Mapper;
import ru.bbnshp.repositories.*;
import ru.bbnshp.services.ImageUploadService;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final BrandRepository brandRepository;

    @Autowired
    private final ShoeRepository shoeRepository;

    @Autowired
    private final TypeRepository typeRepository;

    @Autowired
    private final ShoeSizeRepository shoeSizeRepository;
    @Autowired
    private final SizeRepository sizeRepository;

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final OrderShoeRepository orderShoeRepository;

    @Autowired
    private final ImageUploadService imageUploadService;

    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    public AdminController(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           BrandRepository brandRepository,
                           ShoeRepository shoeRepository,
                           TypeRepository typeRepository,
                           ShoeSizeRepository shoeSizeRepository,
                           SizeRepository sizeRepository,
                           OrderRepository orderRepository,
                           OrderShoeRepository orderShoeRepository,
                           ImageUploadService imageUploadService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.brandRepository = brandRepository;
        this.shoeRepository = shoeRepository;
        this.typeRepository = typeRepository;
        this.shoeSizeRepository = shoeSizeRepository;
        this.sizeRepository = sizeRepository;
        this.orderRepository = orderRepository;
        this.orderShoeRepository = orderShoeRepository;
        this.imageUploadService = imageUploadService;
    }

    @GetMapping("/shoes")
    String getShoes(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication instanceof AnonymousAuthenticationToken){
            return "redirect:/admin/login";
        }
        return "shoes";
    }

    @GetMapping("/shoes/add")
    String getShoesAdd(Model model){
        model.addAttribute("brandList", brandRepository.findAll().stream().map(Mapper::toBrandDto).collect(Collectors.toList()));
        model.addAttribute("typeList", typeRepository.findAll().stream().map(Mapper::toTypeDto).collect(Collectors.toList()));
        model.addAttribute("sexList", Arrays.asList(Sex.values()));
        return "shoesAdd";
    }

    @PostMapping("/shoes/add")
    String getShoesAddProcess(@RequestParam(name = "type") String shoesType,
                              @RequestParam(name = "brand") String shoeBrand,
                              @RequestParam(name = "sex") Sex sex,
                              @RequestParam(name = "name") String name,
                              @RequestParam(name = "color") String color,
                              @RequestParam(name = "price") Integer price,
                              @RequestParam(name = "description") String description,
                              @RequestParam(name = "image") MultipartFile file,
                              @RequestParam(name = "images") MultipartFile[] files,
                              Model model) throws IOException {
        model.addAttribute("brandList", brandRepository.findAll().stream().map(Mapper::toBrandDto).collect(Collectors.toList()));
        model.addAttribute("typeList", typeRepository.findAll().stream().map(Mapper::toTypeDto).collect(Collectors.toList()));
        model.addAttribute("sexList", Arrays.asList(Sex.values()));
        if(!typeRepository.existsByName(shoesType)){
            model.addAttribute("existTypeErr", true);
            return "shoeAdd";
        }
        if(!brandRepository.existsByName(shoeBrand)){
            model.addAttribute("existBrandErr", true);
            return "shoesAdd";
        }
        if(price <= 0){
            model.addAttribute("priceError", true);
            return "shoesAdd";
        }
        Shoe shoe = new Shoe();
        Brand brand = brandRepository.findByName(shoeBrand);
        Type type = typeRepository.findByName(shoesType);
        shoe.setType(type);
        shoe.setBrand(brand);
        shoe.setSex(sex);
        shoe.setName(name);
        shoe.setColor(color);
        shoe.setPrice(price);
        shoe.setDescription(description);
        shoe.setBoughtNum(0);
        String imgPattern = "shoe_" + shoe.hashCode();
        imageUploadService.uploadImage(file, imgPattern + "_main");
        for(int i = 1; i <= files.length; i++){
            imageUploadService.uploadImage(files[i - 1], imgPattern + "_" + i);
        }
        shoe.setImagePattern(imgPattern);
        shoeRepository.save(shoe);
        switch (sex){
            case MALE -> {
                for(Size size: sizeRepository.findAll()){
                    if(size.getValue() >= 39) {
                        ShoeSize shoeSize = new ShoeSize();
                        shoeSize.setSize(size);
                        shoeSize.setExistingNum(0);
                        shoe.addSize(shoeSize);
                    }
                }
            }
            case FEMALE -> {
                for(Size size: sizeRepository.findAll()){
                    if(size.getValue() <= 41) {
                        ShoeSize shoeSize = new ShoeSize();
                        shoeSize.setSize(size);
                        shoeSize.setExistingNum(0);
                        shoe.addSize(shoeSize);
                    }
                }
            }
            case UNISEX -> {
                for(Size size: sizeRepository.findAll()){
                    ShoeSize shoeSize = new ShoeSize();
                    shoeSize.setSize(size);
                    shoeSize.setExistingNum(0);
                    shoe.addSize(shoeSize);
                }
            }
        }
        shoeRepository.save(shoe);
        model.addAttribute("success", true);
        return "shoesAdd";
    }

    @GetMapping("/brand/add")
    String getBrandAdd(Model model){
        model.addAttribute("brandList", brandRepository.findAll().stream().map(Mapper::toBrandDto).collect(Collectors.toList()));
        return "brandAdd";
    }

    @PostMapping("/brand/add")
    String getBrandAddProcess(@RequestParam(name = "name") String name, Model model){
        String nameUp = name.toUpperCase();
        if(brandRepository.existsByName(nameUp)){
            model.addAttribute("nameError", true);
            return "brandAdd";
        }
        Brand brand = new Brand();
        brand.setName(nameUp);
        brandRepository.save(brand);
        model.addAttribute("success", true);
        model.addAttribute("brandList", brandRepository.findAll().stream().map(Mapper::toBrandDto).collect(Collectors.toList()));
        return "brandAdd";
    }

    @GetMapping("/login")
    String getLogin(){
        return "login";
    }

    @PostMapping("/login")
    String loginProcess(@RequestParam(name = "login") String login,
                        @RequestParam(name = "password") String password,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        Model model){
        if(Objects.equals(login, "") || Objects.equals(password, "")){
            model.addAttribute("fieldError", true);
            return "login";
        }
        if(!userRepository.existsByLogin(login)){
            model.addAttribute("loginError", true);
            return "login";
        }
        if(userRepository.findByLogin(login).isPresent() && !(new BCryptPasswordEncoder().matches(password, userRepository.findByLogin(login).get().getPassword()))){
            model.addAttribute("passwordError", true);
            return "login";
        }
        if(userRepository.findByLogin(login).isPresent() && userRepository.findByLogin(login).get().getRole() != UserRole.ADMIN){
            model.addAttribute("roleError", true);
            return "login";
        }
        UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(login, password);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
        securityContextRepository.saveContext(context, request, response);
        return "redirect:/admin/shoes";
    }

    @GetMapping("/procurement")
    String getProcurement(Model model){
        model.addAttribute("shoesList", shoeRepository.findAll().stream().map(Mapper::toShoeDto).toList());
        return "procurement";
    }

    @PostMapping("/procurement/add/{id}")
    String AddProcurement(Model model, @PathVariable Integer id,
                          @RequestParam Map<String,String> sizes){
        model.addAttribute("shoesList", shoeRepository.findAll().stream().map(Mapper::toShoeDto).toList());
        Optional<Shoe> shoeOp = shoeRepository.findById(id);
        if(shoeOp.isPresent()){
            for(ShoeSize size: shoeOp.get().getSizeSet().stream().toList()){
                size.setExistingNum(Integer.valueOf(sizes.get(id + "_" + size.getSize().getValue())));
                shoeSizeRepository.save(size);
            }
        }
        return "redirect:/admin/procurement";
    }

    @GetMapping("/upload")
    String getTestImage(){
        return "testImage";
    }

    @PostMapping("/upload")
    String uploadImage(Model model, @RequestParam(name = "image") MultipartFile file,
                                    @RequestParam(name = "images") MultipartFile[] files,
                                    @RequestParam(name = "name") String name) throws IOException {
        imageUploadService.uploadImage(file, name + "_shoe_1_main");
        model.addAttribute("msg", "Uploaded images: " + file.getOriginalFilename());
        return "testImage";
    }

    @GetMapping("/orders")
    String getOrders(Model model){
        List<OrderDto> orderList = new ArrayList<>(orderRepository.findAll().stream().map(Mapper::toOrderDto).toList());
        orderList.sort(Comparator.comparing(OrderDto::getId));
        model.addAttribute("orderList", orderList);
        return "orders";
    }

    @PostMapping("/orders/change/{id}")
    String changeStatus(Model model, @PathVariable Integer id){
        Optional<Order> orderOptional = orderRepository.findById(id);
        if(orderOptional.isPresent()){
            Order order = orderOptional.get();
            switch (order.getStatus()){
                case ACCEPTED_FOR_PROCESSING -> order.setStatus(OrderStatus.IN_ASSEMBLY);
                case IN_ASSEMBLY -> order.setStatus(OrderStatus.SENT_FOR_DELIVERY);
                case SENT_FOR_DELIVERY -> order.setStatus(OrderStatus.READY_TO_RECEIVE);
                case READY_TO_RECEIVE -> order.setStatus(OrderStatus.RECEIVED);
            }
            orderRepository.save(order);
        }
        else{
            model.addAttribute("orderErr", true);
        }
        List<OrderDto> orderList = new ArrayList<>(orderRepository.findAll().stream().map(Mapper::toOrderDto).toList());
        orderList.sort(Comparator.comparing(OrderDto::getId));
        model.addAttribute("orderList", orderList);
        return "orders";
    }
}
