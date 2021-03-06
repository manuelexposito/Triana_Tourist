package com.salesianostriana.trianatouristapp.models.poi;


import lombok.*;
import com.salesianostriana.trianatouristapp.models.category.Category;
import com.salesianostriana.trianatouristapp.models.route.Route;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Poi implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;


    private String location;

    @Lob
    private String description;


    private LocalDate date;


    @ManyToOne
    private Category category;

    @Builder.Default
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "poi_id",
            foreignKey = @ForeignKey(name = "FK_ROUTE_POI")),
            inverseJoinColumns = @JoinColumn(name = "route_id"),
            name = "POI_ROUTE"
    )
    private List<Route> routes = new ArrayList<>();


    private String coverPhoto, photo2, photo3;


    //HELPERS

    public void addToRoute(Route r) {

        this.routes.add(r);
        r.getSteps().add(this);

    }

    public void removeFromRoute(Route r) {

        this.routes.remove(r);
        r.getSteps().remove(this);


    }


}
