package com.salesianostriana.trianatouristapp.models.category.dto;


import com.salesianostriana.trianatouristapp.models.category.Category;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryDtoConverter {


    private final ModelMapper modelMapper;

    public GetCategoryDto convertToDto(Category category){

        return modelMapper.map(category, GetCategoryDto.class);
    }

}
