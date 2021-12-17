package com.salesianostriana.trianatouristapp.services;

import com.salesianostriana.trianatouristapp.errors.exceptions.ListEntityNotFoundException;
import com.salesianostriana.trianatouristapp.models.poi.Poi;
import com.salesianostriana.trianatouristapp.models.poi.dto.GetPOIDto;
import com.salesianostriana.trianatouristapp.models.route.Route;
import org.springframework.stereotype.Service;
import com.salesianostriana.trianatouristapp.repositories.PoiRepository;
import com.salesianostriana.trianatouristapp.services.base.BaseService;

import java.util.List;

@Service
public class PoiService extends BaseService<Poi, Long, PoiRepository> {

    @Override
    public List<Poi> findAll() {
        List<Poi> lista = super.findAll();

        if(lista.isEmpty()){
            throw new ListEntityNotFoundException(GetPOIDto.class);
        }else{
            return lista;
        }

    }

    @Override
    public List<Poi> saveAll(List<Poi> list) {
        return super.saveAll(list);
    }

    public void addPoiToRoute(Poi poi, Route r, RouteService routeService){

        poi.addToRoute(r);
        routeService.save(r);
        save(poi);

    }

}
