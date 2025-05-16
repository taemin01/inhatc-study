package org.example.week5.com.springboot.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.week5.domain.Book;
import org.example.week5.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
//
//# 스프링 Bean Validation, Jakarta Validation에 기본 제공되는 제약 어노테이션들은
//# 이미 내부에 Validator 구현이 되어 있어서 별도의 클래스를 만들 필요가 없다.
// 도메인 비즈니스 로직에 특화된 검증은 직접 어노테이션, Validator 클래스를 만들어야 한다.
// 어노테이션을 만든 클래스는 BookId.java를 보면 된다. 클래스 부분에 @interface로 정의했다.
public class BookIdValidator implements ConstraintValidator<BookId, String> {
    @Autowired
    private BookService bookService;

    @Override
    public void initialize(BookId constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Book book;
        try {
            book = bookService.findByBookId(s);
        } catch (Exception e) {
            return true;
        }
        if(book != null) {
            return false;
        }
        return true;
    }
}
