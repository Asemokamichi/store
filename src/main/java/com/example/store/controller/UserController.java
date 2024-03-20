package com.example.store.controller;

import com.example.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/sign_up")
    public String signUp() {
        return "view/sign_up";
    }

    @PostMapping("/sign_up")
    public String signUpPost(@RequestParam("username") String username,
                             @RequestParam("surname") String surname,
                             @RequestParam("email") String email,
                             @RequestParam("password") String password,
                             @RequestParam("confirm_password") String cnfPassword
    ) {
        System.out.println(username);
        System.out.println(surname);
        System.out.println(email);
        System.out.println(password);
        System.out.println(cnfPassword);
        return "redirect:/product";
    }

    @GetMapping("/sign_in")
    public String signIn() {
        if (userService.getUser() != null) return "redirect:/product";

        return "view/sign_in";
    }

//    @PostMapping("/sign_in")
//    public String signInPost(@RequestParam("email") String email,
//                             @RequestParam("password") String password) {
//        userService.getUserByEmailAndPassword(email, password);
//
//        return "redirect:/product";
//    }
//
//    @GetMapping("/logout")
//    public String logout() {
//        userService.logout();
//        return "redirect:/product";
//    }

}
