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
import ru.bbnshp.entities.Brand;
import ru.bbnshp.entities.Shoe;
import ru.bbnshp.entities.UserRole;
import ru.bbnshp.repositories.BrandRepository;
import ru.bbnshp.repositories.ShoeRepository;
import ru.bbnshp.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    public AdminController(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           BrandRepository brandRepository,
                           ShoeRepository shoeRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.brandRepository = brandRepository;
        this.shoeRepository = shoeRepository;
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
        List<String> brands = new ArrayList<>();
        for(Brand brand: brandRepository.findAll()){
            brands.add(brand.getName());
        }
        model.addAttribute("brands", brands);
        return "shoesAdd";
    }

    @PostMapping("/shoes/add")
    String getShoesAddProcess(@RequestParam(name = "model") String shoesModel,
                              @RequestParam(name = "brand") String shoeBrand,
                              @RequestParam(name = "color") String color,
                              @RequestParam(name = "price") Integer price,
                              @RequestParam(name = "num") Integer num,
                              @RequestParam(name = "description") String description,
                              Model model){
        if(num <= 0){
            model.addAttribute("numError", true);
            List<String> brands = new ArrayList<>();
            for(Brand brand: brandRepository.findAll()){
                brands.add(brand.getName());
            }
            model.addAttribute("brands", brands);
            return "shoesAdd";
        }
        Shoe shoe = new Shoe();
        Brand brand = brandRepository.findByName(shoeBrand);
        shoe.setBrand(brand);
        shoe.setColor(color);
        shoe.setPrice(price);
        shoe.setDescription(description);
        shoe.setRemainingNum(num);
        shoe.setBoughtNum(0);
        shoeRepository.save(shoe);
        return "redirect:/admin/shoes/add";
    }

    @GetMapping("/brand/add")
    String getBrandAdd(Model model){
        List<Brand> brands = brandRepository.findAll();
        model.addAttribute("brandList", brands);
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
