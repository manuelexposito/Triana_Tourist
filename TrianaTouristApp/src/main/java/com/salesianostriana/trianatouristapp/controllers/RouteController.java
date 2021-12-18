package com.salesianostriana.trianatouristapp.controllers;

import com.salesianostriana.trianatouristapp.models.poi.dto.PoiDtoConverter;
import com.salesianostriana.trianatouristapp.models.route.Route;
import com.salesianostriana.trianatouristapp.models.route.dto.GetRouteDto;
import com.salesianostriana.trianatouristapp.models.route.dto.RouteDtoConverter;
import com.salesianostriana.trianatouristapp.services.PoiService;
import com.salesianostriana.trianatouristapp.services.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/route")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;
    private final PoiService poiService;
    private final RouteDtoConverter routeDtoConverter;
    private final PoiDtoConverter poiDtoConverter;


    @GetMapping("/")
    public List<GetRouteDto> findAll(){

        return routeService.findAll().stream()
                .map(r -> routeDtoConverter.convertToDto(r, poiDtoConverter))
                .collect(Collectors.toList());
    }


    //TODO: Get One
    //TODO: Post

    //TODO: AÑADIR Poi a una ruta
    //TODO: ELIMINAR Poi de una ruta

    //TODO: Put
    //TODO: Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        Route route = routeService.findRouteById(id);

        routeService.deleteRoute(route, poiService);

        return ResponseEntity.noContent().build();
    }
}
