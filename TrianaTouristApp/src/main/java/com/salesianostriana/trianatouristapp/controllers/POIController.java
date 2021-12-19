package com.salesianostriana.trianatouristapp.controllers;

import com.salesianostriana.trianatouristapp.models.poi.dto.CreatePoiDto;
import com.salesianostriana.trianatouristapp.models.poi.dto.GetPOIDto;
import com.salesianostriana.trianatouristapp.models.poi.dto.PoiDtoConverter;
import com.salesianostriana.trianatouristapp.services.CategoryService;
import com.salesianostriana.trianatouristapp.services.PoiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/poi")
@RequiredArgsConstructor
public class POIController {

    private final PoiService poiService;
    private final CategoryService categoryService;
    private final PoiDtoConverter poiDtoConverter;


    @GetMapping("/")
    public List<GetPOIDto> findAll(){

        return poiService.findAll()
                .stream()
                .map(poiDtoConverter::convertToDto)
                .collect(Collectors.toList());


    }
    //TODO: Validar que no se pueda poner un id menor a 1
    @GetMapping("/{id}")
    public GetPOIDto findOne(@PathVariable Long id){

        GetPOIDto poi = poiDtoConverter.convertToDto(poiService.findPoiById(id));

        return poi;
    }
    //TODO: Post

    @PostMapping("/")
    public ResponseEntity<GetPOIDto> createPoi(@Valid @RequestBody CreatePoiDto poiDto){

        GetPOIDto newPoi = poiDtoConverter.convertToDto(poiService.save(poiDto, categoryService,poiDtoConverter));

        return ResponseEntity.status(HttpStatus.CREATED).body(newPoi);

    }

    //TODO: Put
    //TODO: Delete


}
