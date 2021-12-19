package com.salesianostriana.trianatouristapp.validation.annotations;

import com.salesianostriana.trianatouristapp.validation.validators.NotRepeatableValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotRepeatableValidator.class)
public @interface NotRepeatable {

    String message() default "Las fotos no pueden repetirse";

    Class<?>[] groups() default {};

    Class<? extends Payload> [] payload() default {};

    String coverPhoto();
    String photo2();
    String photo3();

    /*
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @interface List{
        NotRepeatable[] value();
    }*/

}
