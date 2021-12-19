package com.salesianostriana.trianatouristapp.models.poi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;

@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class CreatePoiDto extends PoiDto {

    //TODO : Que se pueda poner una categoría NULL
    private Long categoryId;

}
