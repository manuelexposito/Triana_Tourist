package com.salesianostriana.trianatouristapp.models.category.dto;

import com.salesianostriana.trianatouristapp.validation.annotations.UniqueName;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetCategoryDto {

    private Long id;
    @UniqueName
    @NotEmpty
    private String name;

}
