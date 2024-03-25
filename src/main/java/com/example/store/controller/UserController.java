package com.example.store.controller;

import com.example.store.entity.User;
import com.example.store.entity.UserRole;
import com.example.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        User user = new User();

        user.setUserName(username);
        user.setUserSurname(surname);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(UserRole.USER);

        userService.save(user);
//        user.setUserName(cnfPassword);

        return "redirect:/product";
    }
}
