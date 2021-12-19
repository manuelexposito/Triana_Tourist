package com.salesianostriana.trianatouristapp.controllers;

import com.salesianostriana.trianatouristapp.models.category.dto.CategoryDtoConverter;
import com.salesianostriana.trianatouristapp.models.category.dto.GetCategoryDto;
import com.salesianostriana.trianatouristapp.services.CategoryService;
import lombok.RequiredArgsConstructor;
import com.salesianostriana.trianatouristapp.models.poi.dto.GetPOIDto;
import com.salesianostriana.trianatouristapp.models.poi.dto.PoiDtoConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.salesianostriana.trianatouristapp.services.PoiService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryDtoConverter converter;


    @GetMapping("/")
    public List<GetCategoryDto> findAll(){
        return categoryService.findAll().stream().map(converter::convertToDto).collect(Collectors.toList());
    }

    //TODO: Get One
    //TODO: Post
    //TODO: Put
    //TODO: Delete

}
