package com.salesianostriana.trianatouristapp.validation.validators;

import com.salesianostriana.trianatouristapp.repositories.PoiRepository;
import com.salesianostriana.trianatouristapp.services.PoiService;
import com.salesianostriana.trianatouristapp.validation.annotations.NotRepeatable;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.*;

@RequiredArgsConstructor
public class NotRepeatableValidator implements ConstraintValidator<NotRepeatable, Object> {

    private final PoiRepository poiRepository;
    private String coverPhoto;
    private String photo2;
    private String photo3;

    @Override
    public void initialize(NotRepeatable constraintAnnotation) {
        //ConstraintValidator.super.initialize(constraintAnnotation);
        coverPhoto = constraintAnnotation.coverPhoto();
        photo2 = constraintAnnotation.photo2();
        photo3 = constraintAnnotation.photo3();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {


        String p1= (String) PropertyAccessorFactory.forBeanPropertyAccess(o).getPropertyValue(coverPhoto);
        String p2= (String) PropertyAccessorFactory.forBeanPropertyAccess(o).getPropertyValue(photo2);
        String p3= (String) PropertyAccessorFactory.forBeanPropertyAccess(o).getPropertyValue(photo3);


        List<String> images = new ArrayList<>();
        images.add(p1);

        if(p2 != null)
            images.add(p2);

        if(p3 != null)
            images.add(p3);


        Set<String> set = new HashSet<>();
        for(String p : images){
            if(!set.add(p)){
                return false;
            }
        }


        return true;

       
    }
}
