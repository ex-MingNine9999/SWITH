# SpringBoot Project

  - 책에서 만든 프로젝트을 기반으로 여러 기능을 추가 구현

## Version

  - SpringBoot : 2.1.7.RELEASE
  - DB : 8.0.18 MySQL Community Server
  - Gradle : gradle-4.10.2
  - Jquery : min.js-v3.4.1, form.js-v4.2.2
  - Bootstrap : v3.3.7
  
## Gradle

```$xslt
// Spring
compile('org.springframework.boot:spring-boot-starter-web')
compile('org.springframework.boot:spring-boot-starter-thymeleaf')
compile('org.springframework.boot:spring-boot-starter-data-jpa')

// Database
compile('com.h2database:h2')
compile('mysql:mysql-connector-java')

// Security, OAuth
compile('org.springframework.boot:spring-boot-starter-oauth2-client')
compile('org.springframework.boot:spring-boot-starter-security')

// Google Chart
compile('com.googlecode.json-simple:json-simple:1.1.1')
compile('com.fasterxml.jackson.core:jackson-databind:2.9.4')

// Plugin
compileOnly('org.projectlombok:lombok')
testCompile('org.springframework.boot:spring-boot-starter-test')
```
  
## v1.0
  
  - 게시판 작성자 목록 추가
  - 로그인, 로그아웃 버튼 추가
  - 로그인 폼 개선
  - 소셜로그인(구글, 페이스북, 네이버, 카카오)기능 추가
  
## v1.1
  
  - Spring Security를 이용한 자체 로그인 및 회원가입 구현 + Password Hashing(salt)
  - ajax를 이용한 회원가입 예외처리
  - 게시판 파일 업로드 및 다운로드 기능 추가
  
## v1.2
  
  - 메인페이지 및 강의페이지 추가
  - 네비게이션 바 구현(게시판 탭, 메인페이지, 강의 탭)
  - 동영상 스트리밍 지원
  - 소스 공유를 위해 mysql에서 in-memory db로 변경
  
## v1.3

  - 구글 차트를 이용한 집중도 페이지 구현
  - 게시판 댓글 DB 구현
  
## v1.3.1

  - User session 정보 및 Content session 정보 추가
  - 집중도 처리 restAPI 구현
  
## 구현 예정
  
  - 로그인 예외처리
  - 웹캠 사용
 
## Referenced

  - 소스코드  
      - 게시글 CRUD API : 스프링 부트와 AWS로 혼자 구현하는 웹 서비스(이동욱 저)  
      - 게시글 및 기본 템플릿 : 처음 배우는 스프링 부트 2(김영재 저)
      - 동영상 스트리밍 : https://whiteduck.tistory.com/118
  - 메인 페이지 템플릿 : https://startbootstrap.com/themes/one-page-wonder
  - 로그인 및 회원가입 템플릿 : https://bootsnipp.com/snippets/X04B0
  - 강의 페이지 템플릿 : https://templated.co/fervency