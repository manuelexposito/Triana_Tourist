package com.salesianostriana.trianatouristapp.controllers;

import com.salesianostriana.trianatouristapp.models.poi.Poi;
import com.salesianostriana.trianatouristapp.models.poi.dto.CreatePoiDto;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/route")
@RequiredArgsConstructor
@Validated
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



    @GetMapping("/{id}")
    public GetRouteDto findOne(@PathVariable @Min(value = 1, message = "{id.minimo}") Long id){

        return routeDtoConverter.convertToDto(routeService.findRouteById(id), poiDtoConverter);

    }



    @PostMapping("/")
    public ResponseEntity<GetRouteDto> createRoute(@Valid @RequestBody CreateRouteDto routeDto){

        Route newRoute = routeService.save(routeDto, routeDtoConverter);
        GetRouteDto route = routeDtoConverter.convertToDto(newRoute, poiDtoConverter);

        return ResponseEntity.status(HttpStatus.CREATED).body(route);
    }


    @PostMapping("/{id1}/poi/{id2}")
    public ResponseEntity<GetRouteDto> addPoiToRoute(@Valid @PathVariable("id1") @Min(value = 1, message = "{id.minimo}") Long idRoute, @PathVariable("id2") @Min(value = 1, message = "{id.minimo}") Long idPoi){
        Poi poi = poiService.findPoiById(idPoi);
        Route route = routeService.findRouteById(idRoute);

        poiService.addPoiToRoute(poi, route, route.getSteps(), routeService);

        return ResponseEntity.status(HttpStatus.CREATED).body(routeDtoConverter.convertToDto(route, poiDtoConverter));

    }


    @DeleteMapping("/{id1}/poi/{id2}")
    public ResponseEntity<?> removePoiFromRoute(@PathVariable("id1") @Min(value = 1, message = "{id.minimo}")Long idRoute, @PathVariable("id2") @Min(value = 1, message = "{id.minimo}") Long idPoi){
        Poi poi = poiService.findPoiById(idPoi);
        Route route = routeService.findRouteById(idRoute);

        poiService.removePoiFromRoute(poi, route, routeService);

        return ResponseEntity.noContent().build();

    }

    //TODO: Put
    @PutMapping("/{id}")
    public GetRouteDto editRoute(@Valid @PathVariable @Min(value = 1, message = "{id.minimo}") Long id, @RequestBody CreatePoiDto dto){


        Route routeToBeEdited = routeService.findRouteById(id);
        routeService.edit(routeToBeEdited, dto);
        return routeDtoConverter.convertToDto(routeToBeEdited, poiDtoConverter);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable @Min(value = 1, message = "{id.minimo}") Long id){

        Route route = routeService.findRouteById(id);

        routeService.deleteRoute(route, poiService);

        return ResponseEntity.noContent().build();
    }
}
