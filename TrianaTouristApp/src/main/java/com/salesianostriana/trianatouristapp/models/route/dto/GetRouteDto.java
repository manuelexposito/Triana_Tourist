package com.salesianostriana.trianatouristapp.models.route.dto;

import com.salesianostriana.trianatouristapp.models.poi.Poi;
import com.salesianostriana.trianatouristapp.models.poi.dto.GetPOIDto;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetRouteDto {

    private Long id;

    private String name;

    @Builder.Default
    @UniqueElements
    private List<GetPOIDto> steps = new ArrayList<>();

}
