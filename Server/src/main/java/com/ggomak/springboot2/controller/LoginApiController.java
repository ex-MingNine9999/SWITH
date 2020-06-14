package com.ggomak.springboot2.controller;

import com.ggomak.springboot2.domain.User;
import com.ggomak.springboot2.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginApiController {

    private final LoginService loginService;

    @PostMapping("/api/v2/signup")
    public Long signUp(@RequestBody User requestDto) {
        return loginService.signUp(requestDto);
    }
}
