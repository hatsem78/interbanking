package com.interbanking.interbanking.utils.decorator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = CuilValidator.class)
@Documented
public @interface ValidCuil {
    String message() default "Invalid cuil";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}