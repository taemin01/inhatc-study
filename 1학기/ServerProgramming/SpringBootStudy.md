## SpringBoot 설명
	우선 요청이 들어오면 Domain 객체에 직접 접근하지 않는다. DTO를 통해서 권한 같은 필드들은 고정을 시켜주고 데이터베이스에 저장한다. 
	민감 정보나 권한 정보들에 대한 보안 처리를 1차적으로 해서 넘기는 것이다.
	반대로 프론트에 보내줄 때도 민감 정보는 숨겨서 필요한 정보만 처리하여 보낸다.
	RequestBody는 JSON으로 넘어온 것을 엔티티에 직접 접근하면 해당 필드에 그대로 넣어버린다. 그래서 권한 같은 필드는 요청에 넘어오면 admin이 되어버릴 가능성이 있기에 DTO를 통해서 처리 해준 후 저장한다.

## 계층 정리
	컨트롤러 : HTTP 요청을 받아서 Json을 DTO로 변환하여 Service 계층에 전달한다.
	서비스 : 변환된 DTO 데이터를 받아서 데이터 처리(Entity<->DTO)를 한 후 리포지토리에 저장한다.(save 사용)
	리포지토리 : 인터페이스 파일에 JPARepository<Entity, 기본키 타입>을 지정하여 JPA CRUD 기능을 제공받는다.
	엔티티(도메인) : 우선 DB를 ORM으로 작성하여 데이터베이스 및 테이블을 생성한다. 비즈니스 처리 중심이라고 생각해야 한다.
	DTO : 정보의 보안, 민감 정보 노출 방지 등 보안을 위해 도메인에 직접 접근하지 않고 필드를 통해 처리 후 도메인에 저장한다.

### 파일 구조
	com.example.bookapp
	├── controller          ← HTTP 요청 받는 입구
	├── service             ← 비즈니스 로직 처리
	├── repository          ← DB 처리
	├── domain              ← Entity 정의 (DB와 매핑)
	└── dto                 ← 요청/응답 객체 정의

### 코드
#### domain
```java
// domain/Book.java
package com.example.bookapp.domain;

import jakarta.persistence.*;

@Entity  // 이 클래스는 JPA가 관리하는 "DB 테이블과 매핑되는 객체"임을 나타냄
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키, 자동 증가
    private Long id;

    private String title;
    private String publisher;
    private String category;

    protected Book() {} // JPA가 사용하는 기본 생성자 (무조건 있어야 함)

    public Book(String title, String publisher, String category) {
        this.title = title;
        this.publisher = publisher;
        this.category = category;
    }

    // Getter: 외부에서 값을 읽을 수 있도록 제공
    public String getTitle() { return title; }
    public String getPublisher() { return publisher; }
    public String getCategory() { return category; }
}

```
로직이 들어가는 부분은 아니다.
#### DTO
```java
// dto/BookRequestDto.java
package com.example.bookapp.dto;

import com.example.bookapp.domain.Book;

public class BookRequestDto {
    private String title;
    private String publisher;
    private String category;

    // DTO → Entity 변환 메서드
    public Book toEntity() {
        return new Book(title, publisher, category);
    }

    // Getter/Setter는 생략 가능 (롬복 @Data 사용 가능)
}
```

```java
// dto/BookResponseDto.java
package com.example.bookapp.dto;

import com.example.bookapp.domain.Book;

public class BookResponseDto {
    private String title;
    private String publisher;

    // Entity → DTO 변환 생성자
    public BookResponseDto(Book book) {
        this.title = book.getTitle();
        this.publisher = book.getPublisher();
    }

    // Getter
}
```
DTO는 데이터 전달만 하는 역할이다. 응답용 요청용 DTO에서 각각 엔티티와 DTO로 변환하는 생성자
(메서드)가 필요하다.

#### Repository
```java
// repository/BookRepository.java
package com.example.bookapp.repository;

import com.example.bookapp.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    // Spring Data JPA 에서 제공하는 인터페이스 -> JpaRepository<Entity타입, PK타입> 을 상속받으면
    // save(), findAll(), findById(), delete() 등 DB 기능 자동 제공!
}
```
데이터 저장 로직은 들어가지 않는다. 
쿼리 메소드 작명 규칙도 있음
쿼리 메소드도 추가할 수 있다. (findByTitle)

#### Service
```java
// service/BookService.java
package com.example.bookapp.service;

import com.example.bookapp.domain.Book;
import com.example.bookapp.dto.BookRequestDto;
import com.example.bookapp.dto.BookResponseDto;
import com.example.bookapp.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service  // 이 클래스는 비즈니스 로직을 수행하는 서비스 계층임을 Spring에 알림
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 책 등록 (DTO → Entity → 저장)
    public void registerBook(BookRequestDto dto) {
        Book book = dto.toEntity();          // ① DTO → Entity 변환
        bookRepository.save(book);           // ② Repository를 통해 DB에 저장
    }

    // 책 전체 조회 (Entity 리스트 → DTO 리스트 변환 후 반환)
    public List<BookResponseDto> getAllBooks() {
        return bookRepository.findAll().stream()          // ① 전체 Entity 조회
                .map(BookResponseDto::new)                // ② 하나씩 DTO로 변환
                .collect(Collectors.toList());            // ③ 리스트로 반환
    }
}
```
데이터 처리 로직이 여기에 있으며, 리포지토리에 접근하여 CRUD를 처리하는 부분이다. 비즈니스(핵심) 로직을 처리하는 부분이다. 컨트롤러에서 모든 책임을 여기에 부여한다. (책임 분리)

#### Contoller
```java
// controller/BookController.java
package com.example.bookapp.controller;

import com.example.bookapp.dto.BookRequestDto;
import com.example.bookapp.dto.BookResponseDto;
import com.example.bookapp.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // @Controller + @ResponseBody (JSON 반환)
@RequestMapping("/books") // 공통 URL prefix
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // 책 등록 API
    @PostMapping
    public ResponseEntity<Void> registerBook(@RequestBody BookRequestDto dto) {
        bookService.registerBook(dto);  // JSON → DTO → Entity → 저장
        return ResponseEntity.ok().build();  // 200 OK만 응답
    }

    // 책 목록 조회 API
    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getBooks() {
        List<BookResponseDto> bookList = bookService.getAllBooks(); // 조회 및 DTO 변환
        return ResponseEntity.ok(bookList); // JSON 반환
    }
}

//`@RestController`: 반환값이 모두 JSON
    
//`@RequestBody`: JSON → DTO 자동 변환
    
//`@ResponseBody`: DTO → JSON 자동 변환
```
컨트롤러에서 받은 JSON 데이터를 DTO로 변환하여 서비스 계층에 전달한다. 
서비스 계층에서는 받은 데이터를 통해 엔티티로 변환. 반대일 경우 DTO로 전달하여 컨트롤러에 전송
모든 변환 과정은 반대로도 가능하다. 프론트에 보낼 때는
Entity를 DTO로 DTO를 JSON으로 변환한다.

| 계층         | 역할                    | 주고받는 데이터       |
| ---------- | --------------------- | -------------- |
| Controller | HTTP 요청/응답            | JSON <-> DTO   |
| Service    | 비즈니스(핵심) 로직 처리        | DTO <-> Entity |
| Repository | 데이터베이스 처리             | Entity         |
| DTO        | 데이터 전달(민감 정보 제외 처리 후) | 요청, 응답 DTO     |
| Entity     | DB와 매핑                | JPA가 관리하는 객체   |
#### Bean
	의존성 주입 공부 전 Spring에서 관리하는 Bean에 대해 알고 넘어가야 한다. @Service
	Bean은 밑 표에 있는 어노테이션을 달아야 Spring이 컴포넌트를 스캔하여 해당 클래스를 Bean 객체에 관리한다.
	그리고 Bean 객체로 등록되지 않은 클래스는 Bean에 등록된 클래스를 주입 받을 수 없다.
	

| 어노테이션                        | 의미                |
| ---------------------------- | ----------------- |
| @Component                   | 기본 컴포넌트(Bean 등록용) |
| @Service                     | 비즈니스 로직 계층에서 사용   |
| @Repository                  | DB 연동 계층에서 사용     |
| @Controller, @RestController | 웹 요청 처리 계층        |
모든 어노테이션은 @Component 기반으로 만들어진 것들이다. Spring이 관리하는 Bean 객체로 등록된다.

#### IoC(제어의 역전) 컨테이너
	개발자가 객체를 직접 만들어 관리, 주입 해야 하지만 Spring에서는 이 컨테이너가 자동으로 해준다. 따라서 Bean 자동화는 다 IoC 컨테이너 덕분이다.

#### 전체 흐름
@SpringBootApplication 실행
        ↓
@ComponentScan으로 Bean 클래스 탐색
        ↓
Bean 등록 → 의존성 파악
        ↓
필요한 Bean부터 객체 생성
        ↓
생성자나 @Autowired로 의존성 자동 주입
        ↓
ApplicationContext에 Bean 저장
        ↓
필요한 곳에 Bean 자동 주입

IoC는 개념, 이 개념을 구현하는 것이 DI(의존성 주입) 방식이다.

IoC 실제 구현체
```java
ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
BookService service = context.getBean(BookService.class);
```
AnnotationConfigApplicationContext이 컨테이너를 통해서 Bean을 생성하고 관리한다. 이것을 IoC 컨테이너라고 부른다.
#### 의존성 주입
```java
private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
// 이 코드는 의존성 주입 코드이다. 
```

```java
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final String someInfo;

    // 생성자 1: 실제 의존성 주입을 위한 생성자
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.someInfo = "default";
    }

    // 생성자 2: 테스트나 다른 용도로 만든 생성자
    public BookService(BookRepository bookRepository, String someInfo) {
        this.bookRepository = bookRepository;
        this.someInfo = someInfo;
    }

    ...
}
```
생성자가 여러개일 경우 @Autowired를 통해 자동 주입하게 만들어야 한다. 다른 생성자는 다른 용도로 만들어져 필요할 때만 내부적으로 사용할 수 있게 만든 코드이다.

리포지토리, 서비스 등 Bean으로 등록된 클래스들은 다른 클래스에서 요구하면 Spring이 자동으로 해당 Bean을 주입해준다.

서비스에서 Bean으로 등록된 리포지토리를 요구하면 Springdl 자동으로 Bean을 주입해주는 형식이다.

