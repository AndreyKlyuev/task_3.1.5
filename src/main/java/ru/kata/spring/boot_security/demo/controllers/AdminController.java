package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.Services.AdminService;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.util.UserValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserValidator userValidator;
    private final AdminService adminService;

    @Autowired
    public AdminController(UserValidator userValidator, AdminService adminService) {
        this.userValidator = userValidator;
        this.adminService = adminService;
    }

 // users - admin
    @GetMapping()
    public String adminPlace(Model model){
        model.addAttribute(adminService.allUsers());
        return "admin";
    }
    @GetMapping("/new")
    public String newUser(@ModelAttribute("user")User user){
        return "new";
    }
    @PostMapping("/new")
    public String performNewUser(@ModelAttribute("user")@Valid User user,
                                 BindingResult bindingResult){
       userValidator.validate(user, bindingResult);

       if (bindingResult.hasErrors())
           return "/new";
       adminService.saveUser(user);

       return "redirect:/admin";
    }
}
