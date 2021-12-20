package com.salesianostriana.trianatouristapp.services;

import com.salesianostriana.trianatouristapp.errors.exceptions.ListEntityNotFoundException;
import com.salesianostriana.trianatouristapp.errors.exceptions.SingleEntityNotFoundException;
import com.salesianostriana.trianatouristapp.models.category.Category;
import com.salesianostriana.trianatouristapp.models.category.dto.CategoryDtoConverter;
import com.salesianostriana.trianatouristapp.models.category.dto.CreateCategoryDto;
import com.salesianostriana.trianatouristapp.models.poi.Poi;
import org.springframework.stereotype.Service;
import com.salesianostriana.trianatouristapp.repositories.CategoryRepository;
import com.salesianostriana.trianatouristapp.services.base.BaseService;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService extends BaseService<Category, Long,CategoryRepository> {

    public Category findCategoryById(Long id){

        Optional<Category> category = findById(id);

        if(category.isEmpty()){
            throw new SingleEntityNotFoundException(Category.class, id);
        } else{
            return category.get();
        }


    }

    public List<Category> findAll(){

        List<Category> list = super.findAll();

        if (list.isEmpty()){
            throw new ListEntityNotFoundException(Category.class);
        } else{
            return list;
        }
    }

    public Category save(CreateCategoryDto dto, CategoryDtoConverter converter){

        Category newCategory = converter.createCategoryDtoToCategory(dto);

        return save(newCategory);
    }


    public Category edit(Category category, CreateCategoryDto dto){

        category = Category.builder()
                .id(category.getId())
                .name(dto.getName()).build();

        return save(category);

    }


    public void deleteCategory(Long id, PoiService poiService ){

        Category category = findCategoryById(id);
        List<Poi> poiList = poiService.findAllByCategory(id);
        poiList.forEach(poi -> poi.setCategory(null));

        delete(category);


    }


}
