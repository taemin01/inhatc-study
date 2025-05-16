package org.example.week5.com.springboot.validator;

import org.example.week5.domain.Book;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class UnitsInStockValidator implements Validator {
    public boolean supports(Class<?> clazz) {
        return Book.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        Book book = (Book) target;
        System.out.println("price : " + book.getUnitPrice() + "\n stock : " + book.getUnitsInStock());
        if(book.getUnitPrice() != null && book.getUnitPrice().intValue()>=10000 && book.getUnitsInStock()>99) {
            errors.rejectValue("unitsInStock", "UnitInStockValidator.message", "가격이 10000원 이상인 경우에는 99개 이상을 등록할 수 없습니다.");
        }
    }
}
