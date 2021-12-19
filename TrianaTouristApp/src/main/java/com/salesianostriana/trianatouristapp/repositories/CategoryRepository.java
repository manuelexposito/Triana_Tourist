package com.salesianostriana.trianatouristapp.repositories;

import com.salesianostriana.trianatouristapp.models.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CategoryRepository extends JpaRepository<Category, Long> {



}
