package com.salesianostriana.trianatouristapp.controllers;

import com.salesianostriana.trianatouristapp.models.poi.dto.PoiDtoConverter;
import com.salesianostriana.trianatouristapp.models.route.Route;
import com.salesianostriana.trianatouristapp.models.route.dto.CreateRouteDto;
import com.salesianostriana.trianatouristapp.models.route.dto.GetRouteDto;
import com.salesianostriana.trianatouristapp.models.route.dto.RouteDtoConverter;
import com.salesianostriana.trianatouristapp.services.PoiService;
import com.salesianostriana.trianatouristapp.services.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping("/")
    public ResponseEntity<GetRouteDto> createRoute(@Valid @RequestBody CreateRouteDto routeDto){

        Route newRoute = routeService.save(routeDto, routeDtoConverter);
        GetRouteDto route = routeDtoConverter.convertToDto(newRoute, poiDtoConverter);

        return ResponseEntity.status(HttpStatus.CREATED).body(route);
    }

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
