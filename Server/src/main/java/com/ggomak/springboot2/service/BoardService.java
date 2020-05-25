package com.ggomak.springboot2.service;

import com.ggomak.springboot2.domain.Board;
import com.ggomak.springboot2.domain.Files;
import com.ggomak.springboot2.domain.User;
import com.ggomak.springboot2.domain.enums.BoardType;
import com.ggomak.springboot2.dto.RequestFileDto;
import com.ggomak.springboot2.oauthsecurity.auth.dto.SessionUser;
import com.ggomak.springboot2.repository.BoardRepository;
import com.ggomak.springboot2.repository.FileRepository;
import com.ggomak.springboot2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final FileRepository fileRepository;
    private final FileService fileService;

    public Page<Board> findBoardList(Pageable pageable, BoardType boardType) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize(), Sort.by("id").descending());

        return boardRepository.findAllByBoardType(pageable, boardType);
    }

    // 게시글 읽기
    @Transactional
    public Board findBoardById(Long id) {
        return boardRepository.getOne(id);
    }

    // 게시글 등록 및 파일 업로드
    @Transactional
    public Long save(RequestFileDto requestFileDto, SessionUser sessionUser, MultipartFile multipartFile) {

        if(multipartFile != null) { // 첨부파일이 있는 경우
            String fileTimeStamp = fileService.upload(multipartFile);

            Optional<User> user = userRepository.findByEmailAndSocialType(sessionUser.getEmail(), sessionUser.getSocialType());
            Files files = fileRepository.findByStoredFileName(fileTimeStamp + "_" + multipartFile.getOriginalFilename());

            return boardRepository.save(requestFileDto.toEntity(user.get(), files)).getId();
        }

        else{   // 첨부파일이 없는 경우
            Optional<User> user = userRepository.findByEmailAndSocialType(sessionUser.getEmail(), sessionUser.getSocialType());
            return boardRepository.save(requestFileDto.toEntity(user.get(), null)).getId();
        }
    }

    // 게시글 수정
    @Transactional
    public Long update(Long id, Board requestDto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        board.update(requestDto.getTitle(), requestDto.getSubTitle(), requestDto.getContent(), requestDto.getBoardType());

        return id;
    }

    // 게시글 삭제
    @Transactional
    public void delete (Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        boardRepository.delete(board);
    }

    // 파일 다운로드
    @Transactional
    public Board filedown (Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return board;
    }
}
