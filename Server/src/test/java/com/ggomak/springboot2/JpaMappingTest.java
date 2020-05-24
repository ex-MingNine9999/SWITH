package com.ggomak.springboot2;

import com.ggomak.springboot2.domain.Board;
import com.ggomak.springboot2.domain.User;
import com.ggomak.springboot2.domain.enums.BoardType;
import com.ggomak.springboot2.repository.BoardRepository;
import com.ggomak.springboot2.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaMappingTest {

    private final String boardTestTitle = "테스트";
    private final String email = "test@test.com";

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @Before // 테스트가 진행되기 전 실행되는 부분
    public void init() {
        User user = userRepository.save(User.builder()
                .name("ggomak")
                .password("test")
                .email(email)
                .build());

        boardRepository.save(Board.builder()
                .title(boardTestTitle)
                .subTitle("subTitleTest")
                .content("content")
                .boardType(BoardType.free)
                .user(user)
                .build());
    }

    @Test   // 테스트 코드
    public void 제대로_생성되었는지_테스트() {
        Optional<User> user = userRepository.findByEmail(email);  // User 테이블에서 email로 정보를 가져옴

        assertThat(user.get().getName(), is("ggomak"));   // 테스트
        assertThat(user.get().getPassword(), is("test"));
        assertThat(user.get().getEmail(), is(email));

        Board board = boardRepository.findByUser(user.get()); // board 테이블에서 user로 정보를 가져옴

        assertThat(board.getTitle(), is(boardTestTitle));   // 테스트
        assertThat(board.getSubTitle(), is("subTitleTest"));
        assertThat(board.getContent(), is("content"));
        assertThat(board.getBoardType(), is(BoardType.free));
    }
}
