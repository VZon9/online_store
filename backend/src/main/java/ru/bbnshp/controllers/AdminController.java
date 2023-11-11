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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.bbnshp.entities.*;
import ru.bbnshp.repositories.*;

import java.util.*;

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

    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    public AdminController(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           BrandRepository brandRepository,
                           ShoeRepository shoeRepository,
                           TypeRepository typeRepository,
                           ShoeSizeRepository shoeSizeRepository,
                           SizeRepository sizeRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.brandRepository = brandRepository;
        this.shoeRepository = shoeRepository;
        this.typeRepository = typeRepository;
        this.shoeSizeRepository = shoeSizeRepository;
        this.sizeRepository = sizeRepository;
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
        model.addAttribute("brandList", brandRepository.findAll());
        model.addAttribute("typeList", typeRepository.findAll());
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
                              Model model){
        model.addAttribute("brandList", brandRepository.findAll());
        model.addAttribute("typeList", typeRepository.findAll());
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
        shoeRepository.save(shoe);

        shoe = shoeRepository.getReferenceById(shoeRepository.findAll().size());
        for(Size size: sizeRepository.findAll()){
            ShoeSize shoeSize = new ShoeSize();
            shoeSize.setSize(size);
            shoeSize.setExistingNum(0);
            shoe.addSize(shoeSize);
            shoeSizeRepository.save(shoeSize);
        }
        shoeRepository.save(shoe);
        model.addAttribute("success", true);
        return "shoesAdd";
    }

    @GetMapping("/brand/add")
    String getBrandAdd(Model model){
        model.addAttribute("brandList", brandRepository.findAll());
        return "brandAdd";
    }

    @PostMapping("/brand/add")
    String getBrandAddProcess(@RequestParam(name = "name") String name, Model model){
        List<Brand> brands = brandRepository.findAll();
        model.addAttribute("brandList", brands);
        String nameUp = name.toUpperCase();
        if(brandRepository.existsByName(nameUp)){
            model.addAttribute("nameError", true);
            return "brandAdd";
        }
        Brand brand = new Brand();
        brand.setName(nameUp);
        brandRepository.save(brand);
        model.addAttribute("success", true);
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
}
