package com.ggomak.springboot2.controller;

import com.ggomak.springboot2.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @reference : http://aodis.egloos.com/5962812 * @modified : whiteduck
 */

@RestController
@RequiredArgsConstructor
public class ContentApiController {

    private final ContentService contentService;

    @GetMapping("/api/v1/content/{content_id}")
    public void getContentMediaVideo(@PathVariable String content_id,
                                     HttpServletRequest request, HttpServletResponse response) throws IOException {

        long id = Long.parseLong(content_id);

        contentService.getContent(id, request, response);

    }
}