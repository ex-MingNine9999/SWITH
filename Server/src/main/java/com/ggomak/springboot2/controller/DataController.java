package com.ggomak.springboot2.controller;

import com.ggomak.springboot2.oauthsecurity.annotation.LoginUser;
import com.ggomak.springboot2.oauthsecurity.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class DataController {

    @GetMapping("/data/{content_id}")
    public String data(Model model, @LoginUser SessionUser user, @PathVariable Long content_id) {

        model.addAttribute("sessionUser", user);
        model.addAttribute("content_id", content_id);

        return "chart";
    }
}
