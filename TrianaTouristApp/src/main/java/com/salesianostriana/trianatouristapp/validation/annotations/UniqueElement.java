package com.salesianostriana.trianatouristapp.validation.annotations;

import com.salesianostriana.trianatouristapp.validation.validators.UniqueElementValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueElementValidator.class)
public @interface UniqueElement {

    String message() default "Los elementos no pueden repetirse";

    Class<?>[] groups() default {};

    Class<? extends Payload> [] payload() default {};

    //String  element();

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @interface List{
        UniqueElement[] value();
    }

}
