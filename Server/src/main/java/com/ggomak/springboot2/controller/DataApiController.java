package com.ggomak.springboot2.controller;

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

        String sessionAddress = request.getRemoteAddr();

        dataService.save(sessionAddress, data);
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
