package com.salesianostriana.trianatouristapp;

import com.salesianostriana.trianatouristapp.models.category.Category;
import com.salesianostriana.trianatouristapp.models.route.Route;
import com.salesianostriana.trianatouristapp.services.CategoryService;
import com.salesianostriana.trianatouristapp.services.RouteService;
import lombok.RequiredArgsConstructor;
import com.salesianostriana.trianatouristapp.models.poi.Poi;
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
                .name("De cerveceo").build();
        Category c2 = Category.builder()
                .name("De tapeo").build();

        categoryService.saveAll(List.of(c1, c2));


        Poi p1 = Poi.builder()
                .name("Sitio guapardo")
                .location("Triana")
                .description("Un sitio bastante perron")
                .date(LocalDate.of(1990, 2, 20))
                .coverPhoto("https://www.visitarsevilla.com/wp-content/uploads/2020/05/triana-2.jpg")
                .photo2("https://www.viajes-carrefour.com/blog/wp-content/uploads/2018/02/vacaciones-semana-santa-en-sevilla-viajes-carrefour-baja.jpg")
                .photo3("https://offloadmedia.feverup.com/sevillasecreta.co/wp-content/uploads/2019/05/20101627/38695856525_1fb4e739a2_b.jpg")
                .category(c1)
                .build();

        Poi p2 =Poi.builder()
                .name("Sitio guapardo 2")
                .location("Toledo")
                .description("Un sitio interesante")
                .date(LocalDate.of(1997, 2, 20))
                .coverPhoto("https://unafoto.jpg")
                .category(c2).build();


        List<Poi> pois = poiService.saveAll(List.of(p1, p2));

        Route r1 = Route.builder()
                .name("Ruta guapilla")
                .build();

        routeService.save(r1);


        poiService.addPoiToRoute(pois.get(0), r1, routeService);
        poiService.addPoiToRoute(pois.get(1), r1, routeService);

      //pois.stream().forEach(p -> poiService.addPoiToRoute(p, r1, routeService));



        //Route r2 = Route.builder().build();

    }

}
