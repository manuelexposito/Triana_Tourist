package com.salesianostriana.trianatouristapp.controllers;

import com.salesianostriana.trianatouristapp.models.poi.Poi;
import com.salesianostriana.trianatouristapp.models.poi.dto.CreatePoiDto;
import com.salesianostriana.trianatouristapp.models.poi.dto.GetPOIDto;
import com.salesianostriana.trianatouristapp.models.poi.dto.PoiDtoConverter;
import com.salesianostriana.trianatouristapp.services.CategoryService;
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
@RequestMapping("/poi")
@RequiredArgsConstructor
@Validated
public class POIController {

    private final PoiService poiService;
    private final CategoryService categoryService;
    private final RouteService routeService;
    private final PoiDtoConverter poiDtoConverter;


    @GetMapping("/")
    public List<GetPOIDto> findAll(){

        return poiService.findAll()
                .stream()
                .map(poiDtoConverter::convertToDto)
                .collect(Collectors.toList());


    }

    @GetMapping("/{id}")
    public GetPOIDto findOne(@PathVariable @Min(value = 1, message = "{id.minimo}") Long id){

        GetPOIDto poi = poiDtoConverter.convertToDto(poiService.findPoiById(id));

        return poi;
    }

    @PostMapping("/")
    public ResponseEntity<GetPOIDto> createPoi(@Valid @RequestBody CreatePoiDto poiDto){

        GetPOIDto newPoi = poiDtoConverter.convertToDto(poiService.save(poiDto, categoryService,poiDtoConverter));

        return ResponseEntity.status(HttpStatus.CREATED).body(newPoi);

    }


    @PutMapping("/{id}")
    public GetPOIDto editPoi(@Valid @PathVariable @Min(value = 1, message = "{id.minimo}") Long id, @RequestBody CreatePoiDto dto){
        Poi poiToBeEdited = poiService.findPoiById(id);
        poiService.edit(poiToBeEdited, dto,categoryService);

        return poiDtoConverter.convertToDto(poiToBeEdited);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePoi(@PathVariable @Min(value = 1, message = "{id.minimo}") Long id){


        Poi poi = poiService.findPoiById(id);
        poiService.deletePoi(poi, routeService);
        return ResponseEntity.noContent().build();
    }
}
