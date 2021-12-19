package com.salesianostriana.trianatouristapp.models.route.dto;

import com.salesianostriana.trianatouristapp.models.poi.Poi;
import com.salesianostriana.trianatouristapp.models.poi.dto.GetPOIDto;
import lombok.*;

import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetRouteDto {

    private String name;

    private List<GetPOIDto> steps = new ArrayList<>();

}
