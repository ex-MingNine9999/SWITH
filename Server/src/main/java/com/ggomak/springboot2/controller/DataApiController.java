package com.ggomak.springboot2.controller;

import com.ggomak.springboot2.domain.Content;
import com.ggomak.springboot2.domain.enums.SocialType;
import com.ggomak.springboot2.oauthsecurity.annotation.LoginUser;
import com.ggomak.springboot2.oauthsecurity.auth.dto.SessionUser;
import com.ggomak.springboot2.service.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.StringTokenizer;

@RestController
@RequiredArgsConstructor
public class DataApiController {

    private final DataService dataService;

    @PostMapping("/api/v3/datasave")
    public void dataSave(HttpServletRequest request, @RequestBody String data) {

        String sessionAddress = request.getHeader("host");

        String concenData = data.substring(2);
        StringTokenizer tokens = new StringTokenizer(data);
        String contentId = tokens.nextToken(":") ;

        dataService.save(sessionAddress, Long.parseLong(contentId), concenData);
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
