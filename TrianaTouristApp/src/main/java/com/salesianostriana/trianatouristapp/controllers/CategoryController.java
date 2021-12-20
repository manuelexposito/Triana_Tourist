package com.salesianostriana.trianatouristapp.controllers;

import com.salesianostriana.trianatouristapp.models.category.Category;
import com.salesianostriana.trianatouristapp.models.category.dto.CategoryDtoConverter;
import com.salesianostriana.trianatouristapp.models.category.dto.CreateCategoryDto;
import com.salesianostriana.trianatouristapp.models.category.dto.GetCategoryDto;
import com.salesianostriana.trianatouristapp.services.CategoryService;
import lombok.RequiredArgsConstructor;
import com.salesianostriana.trianatouristapp.models.poi.dto.GetPOIDto;
import com.salesianostriana.trianatouristapp.models.poi.dto.PoiDtoConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.salesianostriana.trianatouristapp.services.PoiService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@Validated
public class CategoryController {

    private final CategoryService categoryService;
    private final PoiService poiService;
    private final CategoryDtoConverter converter;


    @GetMapping("/")
    public List<GetCategoryDto> findAll(){
        return categoryService.findAll().stream().map(converter::convertToDto).collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public GetCategoryDto findOne(@PathVariable @Min(value = 1, message = "{id.minimo}") Long id){

        return converter.convertToDto(categoryService.findCategoryById(id));

    }

    @PostMapping("/")
    public ResponseEntity<GetCategoryDto> createCategory(@Valid @RequestBody CreateCategoryDto dto){

        GetCategoryDto newCat = converter.convertToDto(categoryService.save(dto, converter));

        return ResponseEntity.status(HttpStatus.CREATED).body(newCat);

    }

    @PutMapping("/{id}")
    public GetCategoryDto editCategory(@Valid @PathVariable @Min(value = 1, message = "{id.minimo}") Long id, @RequestBody CreateCategoryDto dto){

        Category categoryToBeEdited = categoryService.findCategoryById(id);
        categoryService.edit(categoryToBeEdited, dto);

        return converter.convertToDto(categoryToBeEdited);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable @Min(value = 1, message = "{id.minimo}") Long id){

        categoryService.deleteCategory(id, poiService);
        return ResponseEntity.noContent().build();
    }
}
