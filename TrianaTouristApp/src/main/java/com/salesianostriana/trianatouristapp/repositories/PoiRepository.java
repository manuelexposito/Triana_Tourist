package com.salesianostriana.trianatouristapp.repositories;

import com.salesianostriana.trianatouristapp.models.poi.Poi;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Primary
public interface PoiRepository extends JpaRepository<Poi, Long> {

    @Query("""
            SELECT p
            FROM Poi p
            WHERE category.id = :id
            """)
    List<Poi> findAllByCategory(@Param("id") Long id);

}
