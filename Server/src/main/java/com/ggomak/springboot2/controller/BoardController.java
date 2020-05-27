package com.ggomak.springboot2.controller;

import com.ggomak.springboot2.domain.enums.BoardType;
import com.ggomak.springboot2.oauthsecurity.annotation.LoginUser;
import com.ggomak.springboot2.oauthsecurity.auth.dto.SessionUser;
import com.ggomak.springboot2.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 공지사항 목록
    @GetMapping("/board/notice")
    public String noticeList(@PageableDefault Pageable pageable, Model model, @LoginUser SessionUser user) {

        model.addAttribute("sessionUser", user);
        model.addAttribute("boardList", boardService.findBoardList(pageable, BoardType.notice));

        return "board/noticelist";
    }

    // 자유게시판 목록
    @GetMapping("/board/free")
    public String freeList(@PageableDefault Pageable pageable, Model model, @LoginUser SessionUser user) {

        model.addAttribute("sessionUser", user);
        model.addAttribute("boardList", boardService.findBoardList(pageable, BoardType.free));

        return "board/freelist";
    }

    // 게시글 작성
    @GetMapping("/board")
    public String board(@RequestParam(value = "id", defaultValue = "0") Long id, Model model, @LoginUser SessionUser user) {

        model.addAttribute("sessionUser", user);
        model.addAttribute("board", boardService.findBoardById(id));

        return "board/form";
    }

}
