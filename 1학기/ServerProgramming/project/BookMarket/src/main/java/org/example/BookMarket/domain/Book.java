package org.example.BookMarket.domain;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import org.example.BookMarket.validator.BookId;
import java.math.BigDecimal;

@Data
public class Book {
    //messages.properties에 정의된 메시지 사용
    //어노테이션에 메시지 정의해서 사용할 수 있는 듯
    //밑에 나오는 어노테이션들은 유효성 검사에 사용하는 어노테이션들이다.
    //@BookId 처럼 커스텀 어노테이션들은 Validator를 직접 만들어서 사용해야 한다.
    @BookId
    @Pattern(regexp="ISBN[1-9]+", message = "{Pattern.book.bookId}")
    private String bookId; //도서 ID

    @Size(min=4, max=50)
    private String name; //도서 제목

    @Min(value=0)
    @Digits(integer = 8, fraction = 2)
    @NotNull
    private BigDecimal unitPrice; //가격

    private String author; //저자
    private String description; //설명
    private String publisher; //출판사
    private String category; //분류
    private long unitsInStock; //재고수
    private String releaseDate; //출판일(월/년)
    private String condition; //신규도서 or 중고도서 or 전자책
    private String imageFileName; //도서 이미지 파일
    private MultipartFile bookImage;

//    public void setUnitPrice(BigDecimal unitPrice) { //출력 부분에서 해줄 듯
//        DecimalFormat df = new DecimalFormat("###,###");
//        this.unitPrice = df.format(unitPrice);
//    }
}
