package com.salesianostriana.trianatouristapp.models.route;


import lombok.*;
import com.salesianostriana.trianatouristapp.models.poi.Poi;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Route implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Builder.Default
    @ManyToMany
    private List<Poi> steps = new ArrayList<>();

}
