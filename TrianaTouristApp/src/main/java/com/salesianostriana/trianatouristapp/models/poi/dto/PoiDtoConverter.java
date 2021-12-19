package com.salesianostriana.trianatouristapp.models.poi.dto;

import com.salesianostriana.trianatouristapp.services.CategoryService;
import lombok.RequiredArgsConstructor;
import com.salesianostriana.trianatouristapp.models.poi.Poi;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PoiDtoConverter {

    private final ModelMapper modelMapper;

    public GetPOIDto convertToDto(Poi poi){

        return modelMapper.map(poi, GetPOIDto.class);

    }

    public Poi createDtoToPoi(CreatePoiDto dto){

        return Poi.builder()
                .name(dto.getName())
                .location(dto.getLocation())
                .date(dto.getDate())
                .description(dto.getDescription())
                .coverPhoto(dto.getCoverPhoto())
                .photo2(dto.getPhoto2())
                .photo3(dto.getPhoto3())
                .build();


    }



}
