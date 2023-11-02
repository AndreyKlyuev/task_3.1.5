package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.util.UserValidator;

import javax.validation.Valid;
import java.util.List;

@Controller
//@RequestMapping("/admin")
public class AdminController {
    private final UserValidator userValidator;
    private final UserService adminService;

    @Autowired
    public AdminController(UserValidator userValidator, UserService adminService) {
        this.userValidator = userValidator;
        this.adminService = adminService;
    }
    @GetMapping("/admin")
    public String getAdminPage(@CurrentSecurityContext(expression = "authentication.principal") User principal,
                               Model model) {
        model.addAttribute("autUser", principal);
        model.addAttribute("newUser", new User());
        model.addAttribute("users", adminService.allUsers());
        model.addAttribute("allRoles", adminService.findAllRoles());
        return "administrator";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute User user) {
        adminService.saveUser(user);
        return "redirect:/admin";
    }

    @PatchMapping("/update")
    public String updateUser(@ModelAttribute User user) {
        adminService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        adminService.deleteUser(id);
        return "redirect:/admin";
    }
}

//    @GetMapping()
//    public String adminPage(@CurrentSecurityContext(expression = "authentication.principal") User principal,
//                            Model model){
//        model.addAttribute("users",adminService.allUsers());
//        model.addAttribute("autUser", principal);
//        model.addAttribute("newUser", new User());
//        model.addAttribute("allRoles",adminService.findAllRoles());
//        return "administrator";
//    }
//
//    @PostMapping("/create")
//    public String performNewUser(@ModelAttribute("user")@Valid User user,
//                                 BindingResult bindingResult){
//        userValidator.validate(user, bindingResult);
//        adminService.saveUser(user);
//
//        return "redirect:/admin";
//    }
//    @GetMapping()
//    public String edit(Model model, @RequestParam("id") long id) {
//        List<Role> role = adminService.findAllRoles();
//        model.addAttribute("user", adminService.findUser(id));
//        model.addAttribute("roles", role);
//        return "edit";
//    }
//
//    @PatchMapping("/update")
//    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
//                         @RequestParam("id") long id,Model model) {
//        if (bindingResult.hasErrors()) {
//            return "edit";
//        }
//
//        adminService.updateUser(user, id);
//        return "redirect:/admin";
//    }



