package com.salesianostriana.trianatouristapp.models.poi.dto;

import lombok.*;
import com.salesianostriana.trianatouristapp.models.category.Category;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class GetPOIDto extends PoiDto{

    private String categoryName;

}
