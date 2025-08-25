package org.example.BookMarket.validator;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Constraint(validatedBy = BookIdValidator.class) //검증을 수행할 Validator를 지정한다. 직접 만든 Validator 클래스를 지정한다.
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE}) //어노테이션 적용 대상을 설정한다.
@Retention(RetentionPolicy.RUNTIME) //실행 시점까지 유지하는 설정값
@Documented //문서화용
public @interface BookId {
    String message() default "";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
