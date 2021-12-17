package com.salesianostriana.trianatouristapp.services;

import com.salesianostriana.trianatouristapp.models.category.Category;
import org.springframework.stereotype.Service;
import com.salesianostriana.trianatouristapp.repositories.CategoryRepository;
import com.salesianostriana.trianatouristapp.services.base.BaseService;

@Service
public class CategoryService extends BaseService<Category, Long,CategoryRepository> {



}
