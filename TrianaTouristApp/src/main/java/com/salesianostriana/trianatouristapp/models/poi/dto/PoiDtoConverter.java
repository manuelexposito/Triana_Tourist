package com.salesianostriana.trianatouristapp.models.poi.dto;

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


}
