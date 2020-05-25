package com.ggomak.springboot2;

import com.ggomak.springboot2.domain.*;
import com.ggomak.springboot2.domain.enums.BoardType;
import com.ggomak.springboot2.domain.enums.RoleType;
import com.ggomak.springboot2.domain.enums.SocialType;
import com.ggomak.springboot2.repository.*;
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

	// DB 초기화(TEST 용 더미데이터 삽입
	@Bean
	public CommandLineRunner runner(UserRepository userRepository, BoardRepository boardRepository, FileRepository fileRepository, ContentRepository contentRepository, DataRepository dataRepository) throws Exception {

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

			Content content = contentRepository.findByContentNumber(Integer.toUnsignedLong(1));

			Data data = dataRepository.save(Data.builder()
					.content(content)
					.user(user)
					.concentrateData("80:90:70:60:70:80:40:50:50:60:60:70:70:80:90:100:90:80:90:80:70:70:60:60:60:60:50:40:30:20:30:40:40:40:60:70:90:80:50")
					.build());
		};
	}

}
