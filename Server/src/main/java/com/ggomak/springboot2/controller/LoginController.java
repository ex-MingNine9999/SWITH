package com.ggomak.springboot2.controller;

import com.ggomak.springboot2.oauthsecurity.annotation.LoginUser;
import com.ggomak.springboot2.oauthsecurity.auth.dto.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String signIn() {
        return "login/login";
    }

    @GetMapping("/signup")
    public String signUp() {
        return "login/signup";
    }

    @GetMapping("/forget")
    public String forget() {
        return "login/forget";
    }
}
