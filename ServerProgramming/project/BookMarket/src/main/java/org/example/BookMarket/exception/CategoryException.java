package org.example.BookMarket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CategoryException extends RuntimeException {
    private String errorMessage;

    public CategoryException() {
        super();
        this.errorMessage = "요청한 도서 분야를 찾을 수 없습니다.";
    }
}
