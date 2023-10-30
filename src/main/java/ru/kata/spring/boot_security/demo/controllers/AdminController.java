package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Services.UserService;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.util.UserValidator;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserValidator userValidator;
    private final UserService adminService;

    @Autowired
    public AdminController(UserValidator userValidator, UserService adminService) {
        this.userValidator = userValidator;
        this.adminService = adminService;
    }

    @GetMapping()
    public String adminPlace(Model model){
        model.addAttribute("users",adminService.allUsers());
        return "admin";
    }
    @GetMapping("/new")
    public String newUser(@ModelAttribute("user")User user, Model model){
        List<Role> role = adminService.findAllRoles();
        model.addAttribute("roles", role);
        return "new";
    }
    @PostMapping("/create")
    public String performNewUser(@ModelAttribute("user")@Valid User user,
                                 BindingResult bindingResult,Model model){
       userValidator.validate(user, bindingResult);
        List<Role> role = adminService.findAllRoles();
        model.addAttribute("roles", role);

       if (bindingResult.hasErrors())
           return "/new";
       adminService.saveUser(user);

       return "redirect:/admin";
    }
    @GetMapping("/edit")
    public String edit(Model model, @RequestParam("id") long id) {
        List<Role> role = adminService.findAllRoles();
        model.addAttribute("user", adminService.findUser(id));
        model.addAttribute("roles", role);
        return "edit";
    }

    @PatchMapping("/update")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @RequestParam("id") long id,Model model) {
        List<Role> role = adminService.findAllRoles();
        model.addAttribute("roles", role);
        if (bindingResult.hasErrors()) {
            return "edit";
        }

        adminService.updateUser(user, id);
        return "redirect:/admin";
    }
    @DeleteMapping("/delete")
    public String delete(@RequestParam("id") long id) {
        adminService.deleteUser(id);
        return "redirect:/admin";
    }
}
