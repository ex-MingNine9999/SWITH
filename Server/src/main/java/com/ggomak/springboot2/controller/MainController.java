package com.ggomak.springboot2.controller;

import com.ggomak.springboot2.oauthsecurity.annotation.LoginUser;
import com.ggomak.springboot2.oauthsecurity.auth.dto.SessionUser;
import com.ggomak.springboot2.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    // 메인 페이지
    @GetMapping({"/main", "/"})
    public String mainPage(HttpServletRequest request, Model model, @LoginUser SessionUser user) {

        model.addAttribute("sessionUser", user);

        String sessionAddress = request.getHeader("host");

        if(user != null){
            mainService.regist(sessionAddress, user);
        }

        return "/main";
    }


}
