package com.ggomak.springboot2;

import com.ggomak.springboot2.domain.Board;
import com.ggomak.springboot2.domain.Content;
import com.ggomak.springboot2.domain.Files;
import com.ggomak.springboot2.domain.User;
import com.ggomak.springboot2.domain.enums.BoardType;
import com.ggomak.springboot2.domain.enums.RoleType;
import com.ggomak.springboot2.domain.enums.SocialType;
import com.ggomak.springboot2.repository.BoardRepository;
import com.ggomak.springboot2.repository.ContentRepository;
import com.ggomak.springboot2.repository.FileRepository;
import com.ggomak.springboot2.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@EnableJpaAuditing
@SpringBootApplication
public class Springboot2Application implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(Springboot2Application.class, args);
	}

	// 200개의 게시글을 만들어 주는 초기화 메소드

	@Bean
	public CommandLineRunner runner(UserRepository userRepository, BoardRepository boardRepository, FileRepository fileRepository, ContentRepository contentRepository) throws Exception {

		return (args) -> {
			User user = userRepository.save(User.builder()
					.name("test")
					.password("test")
					.email("test@gmail.com")
					.roleType(RoleType.USER)
					.socialType(SocialType.ORIGIN)
					.build());

			Files files = fileRepository.save(Files.builder()
					.filePath("/test")
					.fileSize(100)
					.originFileName("test")
					.storedFileName("test")
					.build());

			IntStream.rangeClosed(1, 8).forEach(index ->
					contentRepository.save(Content.builder()
							.contentName("Data")
							.contentNumber(Long.parseLong(String.valueOf(index)))
							.contentSize(Integer.toUnsignedLong(100))
							.contentTime(Integer.toUnsignedLong(200))
							.build())
			);

			IntStream.rangeClosed(1, 100).forEach(index ->
					boardRepository.save(Board.builder()
							.title("게시글" + index)
							.subTitle("순서" + index)
							.content("Content")
							.boardType(BoardType.free)
							.files(files)
							.user(user)
							.build())
			);

			IntStream.rangeClosed(1, 100).forEach(index ->
					boardRepository.save(Board.builder()
							.title("게시글" + index)
							.subTitle("순서" + index)
							.content("Content")
							.boardType(BoardType.notice)
							.files(files)
							.user(user)
							.build())
			);
		};
	}

}
