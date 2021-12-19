package com.salesianostriana.trianatouristapp.validation.annotations;

import com.salesianostriana.trianatouristapp.validation.validators.UniqueNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueNameValidator.class)
public @interface UniqueName {

    String message() default "El nombre debe ser único";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};




}
