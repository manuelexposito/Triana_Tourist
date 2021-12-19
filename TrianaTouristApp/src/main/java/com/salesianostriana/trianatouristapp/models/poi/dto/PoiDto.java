package com.salesianostriana.trianatouristapp.models.poi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.salesianostriana.trianatouristapp.validation.annotations.MapsUbication;
import com.salesianostriana.trianatouristapp.validation.annotations.NotRepeatable;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
//TODO: Anotacion para que no se repitan las fotos
//@NotRepeatable(coverPhoto = "coverPhoto", photo2="photo2", photo3 = "photo3")
public class PoiDto {

    @NotNull(message = "{poi.name.notnull}")
    private String name;

    @NotEmpty
    @MapsUbication(message = "{poi.ubication}")
    private String location;

    @Lob
    private String description;

    @Past
   // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDate date;

    @URL
    @NotNull
    private String coverPhoto;

    @URL
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String photo2, photo3;


}
