package com.salesianostriana.trianatouristapp;

import com.salesianostriana.trianatouristapp.models.category.Category;
import com.salesianostriana.trianatouristapp.models.route.Route;
import com.salesianostriana.trianatouristapp.services.CategoryService;
import com.salesianostriana.trianatouristapp.services.RouteService;
import lombok.RequiredArgsConstructor;
import com.salesianostriana.trianatouristapp.models.poi.Poi;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Component;
import com.salesianostriana.trianatouristapp.services.PoiService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitData {

    private final PoiService poiService;
    private final CategoryService categoryService;
    private final RouteService routeService;

    @PostConstruct
    public void test(){


        Category c1 = Category.builder()
                .name("Monumentos históricos").build();
        Category c2 = Category.builder()
                .name("Plazas").build();
        Category c3 = Category.builder()
                .name("Parques").build();

        categoryService.saveAll(List.of(c1, c2, c3));


        Poi p1 = Poi.builder()
                .name("La Giralda")
                .location("37.38614, -5.99238")
                .description("Fue rematado en el siglo XVI con el campanario, los templetes decrecientes y El Giraldillo. La parte decorada con ladrillo es obra del arquitecto Alí de Gómara. Se puede subir por rampas hasta el campanario, donde se ven unas extraordinarias vistas de la ciudad. Al lado está el Patio de los Naranjos, de estilo almohade con pila visigótica.")
                .date(LocalDate.of(1172, 12, 29))
                .coverPhoto("https://upload.wikimedia.org/wikipedia/commons/thumb/9/93/La_Giralda_August_2012_Seville_Spain.jpg/800px-La_Giralda_August_2012_Seville_Spain.jpg")
                .photo2("https://upload.wikimedia.org/wikipedia/commons/thumb/2/2b/La_Giralda_%28el_campanario_de_la_Catedral_de_Sevilla%29.jpg/800px-La_Giralda_%28el_campanario_de_la_Catedral_de_Sevilla%29.jpg")
                .photo3("https://urbansevilla.es/wp-content/uploads/2019/06/la-giralda-sevilla-airpano.jpg")
                .category(c1)
                .build();

        Poi p2 =Poi.builder()
                .name("Plaza de España")
                .location("37.377261, -5.986598")
                .description("Se construyó con motivo de la Exposición Iberoamericana de 1929, de la cual fue sede Sevilla. Su autor es Aníbal González. Mezcló un estilo inspirado en el Renacimiento, con los elementos típicos de la ciudad: ladrillo visto, cerámica y forja (realizada por Domingo Prida). Su planta es semicircular. Está dominada por 2 torres, una a cada lado del recinto que enmarcan al edificio central, donde se encuentran las estancias. Entre ambas torres corre una red de galerías con arcada de medio punto que tienen salida a diferentes zonas de la plaza, donde se alza una fuente.")
                .date(LocalDate.of(1928, 1, 21))
                .coverPhoto("https://static1-sevilla.abc.es/media/sevilla/2018/11/29/s/portada-plaza-espana-kITG--620x349@abc.jpg")
                .photo2("https://upload.wikimedia.org/wikipedia/commons/b/b6/Plaza_de_Espa%C3%B1a_%28Sevilla%29_-_01.jpg")
                .category(c2).build();


        List<Poi> pois = poiService.saveAll(List.of(p1, p2));

        Route r1 = Route.builder()
                .id(1L)
                .name("Conociendo Sevilla")
                .build();

        routeService.save(r1);


        poiService.addPoiToRoute(pois.get(0), r1, r1.getSteps(), routeService);
        //poiService.addPoiToRoute(pois.get(1), r1, routeService);

    }

}
