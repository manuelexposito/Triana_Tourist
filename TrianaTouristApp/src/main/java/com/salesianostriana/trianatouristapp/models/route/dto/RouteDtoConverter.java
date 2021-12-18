package com.salesianostriana.trianatouristapp.models.route.dto;

import com.salesianostriana.trianatouristapp.models.poi.Poi;
import com.salesianostriana.trianatouristapp.models.poi.dto.PoiDtoConverter;
import com.salesianostriana.trianatouristapp.models.route.Route;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

@Component

public class RouteDtoConverter {


    public GetRouteDto convertToDto(Route route, PoiDtoConverter poiDtoConverter){

        return GetRouteDto.builder()
                .name(route.getName())
                .poi(route.getSteps().stream()
                        .map(poiDtoConverter::convertToDto)
                        .collect(Collectors.toList())).build();

    }

}