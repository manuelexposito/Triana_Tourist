package com.salesianostriana.trianatouristapp.repositories;

import com.salesianostriana.trianatouristapp.models.poi.Poi;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
public interface PoiRepository extends JpaRepository<Poi, Long> {




}
