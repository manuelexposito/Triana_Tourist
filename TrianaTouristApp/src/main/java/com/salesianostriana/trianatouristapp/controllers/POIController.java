package com.salesianostriana.trianatouristapp.controllers;

import com.salesianostriana.trianatouristapp.models.poi.dto.GetPOIDto;
import com.salesianostriana.trianatouristapp.models.poi.dto.PoiDtoConverter;
import com.salesianostriana.trianatouristapp.services.PoiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/poi")
@RequiredArgsConstructor
public class POIController {

    private final PoiService poiService;
    private final PoiDtoConverter poiDtoConverter;


    //TODO: Get List


    @GetMapping("/")
    public List<GetPOIDto> findAll(){

        return poiService.findAll()
                .stream()
                .map(poiDtoConverter::convertToDto)
                .collect(Collectors.toList());


    }
    //TODO: Get One
    //TODO: Post
    //TODO: Put
    //TODO: Delete


}
