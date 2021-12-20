package com.salesianostriana.trianatouristapp.repositories;

import com.salesianostriana.trianatouristapp.models.poi.Poi;
import com.salesianostriana.trianatouristapp.models.route.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

public interface RouteRepository extends JpaRepository<Route, Long> {


    boolean existsByName(String name);
/*
    @Query(value = """
            SELECT POI_ID
            FROM POI_ROUTE
            WHERE ROUTE_ID = :id
            GROUP BY POI_ID
            HAVING COUNT(POI_ID) > 1
            """, nativeQuery = true)
    Long findRepeatedPoi(@Param("id") Long id);
*/



}
