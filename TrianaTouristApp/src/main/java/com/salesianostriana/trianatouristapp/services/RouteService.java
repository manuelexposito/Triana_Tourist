package com.salesianostriana.trianatouristapp.services;

import com.salesianostriana.trianatouristapp.errors.exceptions.ListEntityNotFoundException;
import com.salesianostriana.trianatouristapp.errors.exceptions.SingleEntityNotFoundException;
import com.salesianostriana.trianatouristapp.models.poi.Poi;
import com.salesianostriana.trianatouristapp.models.route.Route;
import com.salesianostriana.trianatouristapp.models.route.dto.CreateRouteDto;
import com.salesianostriana.trianatouristapp.models.route.dto.GetRouteDto;
import com.salesianostriana.trianatouristapp.models.route.dto.RouteDtoConverter;
import org.springframework.stereotype.Service;
import com.salesianostriana.trianatouristapp.repositories.RouteRepository;
import com.salesianostriana.trianatouristapp.services.base.BaseService;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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


    public void deleteRoute(Route route, PoiService poiService) {
        //TODO: ARREGLAR EL DELETE
        route.getSteps()
                .forEach(poi -> poi.removeFromRoute(route));

        super.delete(route);
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
