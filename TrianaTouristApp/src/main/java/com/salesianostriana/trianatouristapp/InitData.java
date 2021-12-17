package com.salesianostriana.trianatouristapp;

import lombok.RequiredArgsConstructor;
import com.salesianostriana.trianatouristapp.models.poi.Poi;
import org.springframework.stereotype.Component;
import com.salesianostriana.trianatouristapp.services.PoiService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitData {

    private final PoiService poiService;

    @PostConstruct
    public void test(){

        List<Poi> pois = List.of(

                Poi.builder()
                        .name("Sitio guapardo")
                        .location("Triana")
                        .description("Un sitio bastante perron")
                        .date(LocalDate.of(1990, 2, 20))
                        .coverPhoto("http//unafoto.jpg").build(),
                      /*  .photos(List.of("https://www.visitarsevilla.com/wp-content/uploads/2020/05/triana-2.jpg",
                                "https://www.viajes-carrefour.com/blog/wp-content/uploads/2018/02/vacaciones-semana-santa-en-sevilla-viajes-carrefour-baja.jpg",
                                "https://offloadmedia.feverup.com/sevillasecreta.co/wp-content/uploads/2019/05/20101627/38695856525_1fb4e739a2_b.jpg"))
                                .build(),

                       */



                Poi.builder()
                        .name("Sitio guapardo 2")
                        .location("Toledo")
                        .description("Un sitio interesante")
                        .date(LocalDate.of(1997, 2, 20))
                        .coverPhoto("http//unafoto.jpg").build()



        );

        poiService.saveAll(pois);


    }

}
