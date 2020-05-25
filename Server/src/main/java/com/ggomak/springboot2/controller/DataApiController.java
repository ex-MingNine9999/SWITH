package com.ggomak.springboot2.controller;

import com.ggomak.springboot2.domain.Data;
import com.ggomak.springboot2.oauthsecurity.annotation.LoginUser;
import com.ggomak.springboot2.oauthsecurity.auth.dto.SessionUser;
import com.ggomak.springboot2.service.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DataApiController {

    private final DataService dataService;

    @GetMapping("/api/v3/datasave")
    public String dataSave(Model model, @LoginUser SessionUser user, @RequestParam("data") Data data) {
        dataService.save(user, data);
        model.addAttribute("sessionUser", user);

        return "/chart";
    }

    @GetMapping("/api/v3/dataload")
    public String dataLoad(Model model, @LoginUser SessionUser user, @RequestParam("content_id") Long contentNumber){
        String data = dataService.load(user, contentNumber);

        return data;
    }
}
