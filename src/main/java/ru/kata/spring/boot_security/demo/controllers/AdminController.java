package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.services.UserService;

@Controller
public class AdminController {
    private final UserService adminService;

    @Autowired
    public AdminController(UserService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin")
    public String getAdminPage(@CurrentSecurityContext(expression = "authentication.principal") User principal,
                               Model model) {
        model.addAttribute("autUser", principal);
        model.addAttribute("newUser", new User());
        model.addAttribute("users", adminService.allUsers());
        model.addAttribute("allRoles", adminService.findAllRoles());
        return "admin";
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



