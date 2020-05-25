package com.ggomak.springboot2.controller;

import com.ggomak.springboot2.oauthsecurity.annotation.LoginUser;
import com.ggomak.springboot2.oauthsecurity.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DataController {

    @GetMapping("/data")
    public String data(Model model, @LoginUser SessionUser user) {
        model.addAttribute("sessionUser", user);

        return "/chart";
    }
}
