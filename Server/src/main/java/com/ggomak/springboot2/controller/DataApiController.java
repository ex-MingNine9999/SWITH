package com.ggomak.springboot2.controller;

import com.ggomak.springboot2.domain.Data;
import com.ggomak.springboot2.oauthsecurity.annotation.LoginUser;
import com.ggomak.springboot2.oauthsecurity.auth.dto.SessionUser;
import com.ggomak.springboot2.service.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DataApiController {

    private final DataService dataService;

    @GetMapping("/api/v3/datasave")
    public void dataSave(@LoginUser SessionUser user, @RequestParam("data") Data data) {
        dataService.save(user, data);
    }

    @GetMapping("/api/v3/dataload/{id}")
    public String dataLoad(@LoginUser SessionUser user, @PathVariable Long id){
        String data = dataService.load(user, id);

        if(data == null){
            return "null";
        }

        return data;
    }
}
