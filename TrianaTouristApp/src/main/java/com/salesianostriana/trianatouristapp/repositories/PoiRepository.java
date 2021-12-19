package com.salesianostriana.trianatouristapp.repositories;

import com.salesianostriana.trianatouristapp.models.poi.Poi;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Primary
public interface PoiRepository extends JpaRepository<Poi, Long> {

/*
    @Query("""
            SELECT concat('|',p.cover_photo,'|',p.photo2, '|', p.photo3)
            FROM POI p
            WHERE id = :id
            """)
    String findAllPhotosFromPoi(Long id);
*/

}
