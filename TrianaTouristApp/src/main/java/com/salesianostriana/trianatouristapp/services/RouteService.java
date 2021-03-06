package com.salesianostriana.trianatouristapp.services;

import com.salesianostriana.trianatouristapp.errors.exceptions.ListEntityNotFoundException;
import com.salesianostriana.trianatouristapp.errors.exceptions.SingleEntityNotFoundException;
import com.salesianostriana.trianatouristapp.models.poi.Poi;
import com.salesianostriana.trianatouristapp.models.poi.dto.CreatePoiDto;
import com.salesianostriana.trianatouristapp.models.route.Route;
import com.salesianostriana.trianatouristapp.models.route.dto.CreateRouteDto;
import com.salesianostriana.trianatouristapp.models.route.dto.GetRouteDto;
import com.salesianostriana.trianatouristapp.models.route.dto.RouteDtoConverter;
import org.springframework.stereotype.Service;
import com.salesianostriana.trianatouristapp.repositories.RouteRepository;
import com.salesianostriana.trianatouristapp.services.base.BaseService;

import javax.swing.text.html.Option;
import java.util.*;

@Service
public class RouteService extends BaseService<Route, Long, RouteRepository> {

    @Override
    public List<Route> findAll() {
        List<Route> list = super.findAll();
        if (list.isEmpty()){
            throw new ListEntityNotFoundException(GetRouteDto.class);
        } else{
            return list;
        }

    }

    public Route save(CreateRouteDto dto, RouteDtoConverter converter){
        Route newRoute = converter.createRouteDtoToRoute(dto);

        return save(newRoute);

    }

    public Route edit(Route route, CreatePoiDto dto){

        route = Route.builder()
                .id(route.getId())
                .name(dto.getName())
                .steps(route.getSteps()).build();
        return save(route);

    }


    public void deleteRoute(Route route, PoiService poiService) {

        List<Poi> listaBase = new ArrayList<>();
        listaBase.addAll(route.getSteps());
        for (Iterator<Poi> it = listaBase.iterator(); it.hasNext();){
            Poi p = it.next();
            poiService.removePoiFromRoute(p, route, this);
        }

        delete(route);
    }

    public Route findRouteById(Long id) {
        Optional<Route> r = super.findById(id);

        if(r.isEmpty()){
            throw new SingleEntityNotFoundException(GetRouteDto.class, id);
        } else{

            return r.get();

        }
    }




}
