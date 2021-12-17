package com.salesianostriana.trianatouristapp.models.poi.dto;

import lombok.*;
import com.salesianostriana.trianatouristapp.models.category.Category;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;

@Table(name = "poi")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetPOIDto {

    private String name;

    @NotEmpty
    private String location;

    @Lob
    private String description;

    @Past
    private LocalDate date;
 /*
    @ManyToOne
    private Category category;

    @URL
    @NotNull
    private String coverPhoto;

    @URL
    private List<String> photos;
*/

}
