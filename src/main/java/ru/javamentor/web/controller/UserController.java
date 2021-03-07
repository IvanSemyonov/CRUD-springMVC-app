package ru.javamentor.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.web.model.Role;
import ru.javamentor.web.model.User;
import ru.javamentor.web.service.UserService;

import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "login")
    public String loginPage(Principal principal) {
        if (principal == null) {
            return "/login";
        }
        return "redirect:/user";
    }

    @GetMapping(value = "/user")
    public String user(Principal principal, ModelMap model){
        model.addAttribute("user", userService.getUser(principal.getName()));
        return "/user";
    }

    @GetMapping(value = "registration")
    public String getPageRegistration(@ModelAttribute("newUser") User user) {
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String registration(@ModelAttribute("newUser") User user) {
        userService.addUser(user);
        return "redirect:/login";
    }

    @GetMapping("/users")
    public String printUsers(@ModelAttribute("newUser") User user, ModelMap model){
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String getEditPage(@PathVariable("id") long id, ModelMap model) {
        model.addAttribute("user", userService.getUser(id));
        return "/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable("id") long id, @ModelAttribute("user") User user) {
        userService.edit(user);
        return "redirect:/users";
    }
}
