package com.salesianostriana.trianatouristapp.models.route.dto;

import com.salesianostriana.trianatouristapp.validation.annotations.UniqueName;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRouteDto {

    @UniqueName
    private String name;

}
