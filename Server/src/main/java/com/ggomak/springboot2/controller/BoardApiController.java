package com.ggomak.springboot2.controller;

import com.ggomak.springboot2.domain.Board;
import com.ggomak.springboot2.domain.enums.BoardType;
import com.ggomak.springboot2.dto.BoardTempDto;
import com.ggomak.springboot2.oauthsecurity.annotation.LoginUser;
import com.ggomak.springboot2.oauthsecurity.auth.dto.SessionUser;
import com.ggomak.springboot2.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @GetMapping("/api/v1/posts/{id}")
    public Long read(@PathVariable Long id) {
        return boardService.findBoardById(id).getId();
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody Board requestDto) {
        return boardService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        boardService.delete(id);
        return id;
    }

    // 파일 다운로드 (코드 정리 필요)
    @GetMapping("/api/v1/download/{id}")
    public void download(@PathVariable Long id, HttpServletResponse response, HttpServletRequest request) {

        Board board = boardService.filedown(id);   // 게시글에 첨부된 파일 가져오기

        try {
            File file = new File(board.getFiles().getFilePath() + board.getFiles().getStoredFileName());

            if (file == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }

            response.setContentType("application/download; charset=utf-8");
            response.setContentLengthLong((int) file.length());

            String userAgent = request.getHeader("user-Agent");
            boolean internetExplorer = userAgent.indexOf("MSIE") > -1;

            String fileName = null;

            if (internetExplorer) {
                fileName = URLEncoder.encode(file.getName(), "UTF-8");
            } else {
                fileName = new String(file.getName().getBytes("UTF-8"), "ISO-8859-1");
            }

            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
            response.setHeader("Content-Transfer-Encoding", "binary");

            OutputStream out = response.getOutputStream();
            FileInputStream fis = new FileInputStream(file);
            FileCopyUtils.copy(fis, out);
            out.flush();

            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ex) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                }
            }

        } catch( Exception e) {

        }
    }
}
