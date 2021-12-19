package com.salesianostriana.trianatouristapp.validation.validators;

import com.salesianostriana.trianatouristapp.repositories.RouteRepository;
import com.salesianostriana.trianatouristapp.validation.annotations.UniqueName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueNameValidator implements ConstraintValidator<UniqueName, String> {

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public void initialize(UniqueName constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.hasText(s) && !routeRepository.existsByName(s);
    }
}
