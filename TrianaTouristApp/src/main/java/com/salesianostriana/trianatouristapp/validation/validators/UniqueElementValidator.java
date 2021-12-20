package com.salesianostriana.trianatouristapp.validation.validators;

import com.salesianostriana.trianatouristapp.validation.annotations.UniqueElement;
import org.springframework.beans.PropertyAccessorFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UniqueElementValidator implements ConstraintValidator<UniqueElement, List<Object>> {



    @Override
    public void initialize(UniqueElement constraintAnnotation) {

    }

    @Override
    public boolean isValid(List<Object> object, ConstraintValidatorContext constraintValidatorContext) {

        //Object elementValue = PropertyAccessorFactory.forBeanPropertyAccess(object).getPropertyValue(element);

        List<Object> lista = object;
        Set<Object> set = new HashSet<>();

        for(Object e : lista){
            if(set.add(e) == false){
                return false;
            }
        }
        return true;
    //TODO: Ver Unique Constraint.

    }
}
