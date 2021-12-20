package com.salesianostriana.trianatouristapp.services;

import com.salesianostriana.trianatouristapp.errors.exceptions.ListEntityNotFoundException;
import com.salesianostriana.trianatouristapp.errors.exceptions.RepeatedElementsException;
import com.salesianostriana.trianatouristapp.errors.exceptions.SingleEntityNotFoundException;
import com.salesianostriana.trianatouristapp.models.category.Category;
import com.salesianostriana.trianatouristapp.models.poi.Poi;
import com.salesianostriana.trianatouristapp.models.poi.dto.CreatePoiDto;
import com.salesianostriana.trianatouristapp.models.poi.dto.GetPOIDto;
import com.salesianostriana.trianatouristapp.models.poi.dto.PoiDtoConverter;
import com.salesianostriana.trianatouristapp.models.route.Route;
import org.springframework.stereotype.Service;
import com.salesianostriana.trianatouristapp.repositories.PoiRepository;
import com.salesianostriana.trianatouristapp.services.base.BaseService;

import java.util.List;
import java.util.Optional;

@Service
public class    PoiService extends BaseService<Poi, Long, PoiRepository> {


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

    public Poi findPoiById(Long id){

        Optional<Poi> poi = repository.findById(id);

        if (poi.isEmpty()){
            throw new SingleEntityNotFoundException(Poi.class, id);
        }else{
            return poi.get();
        }

    }


    public Poi save(CreatePoiDto poiDto, CategoryService categoryService, PoiDtoConverter converter){

        Poi newPoi = converter.createDtoToPoi(poiDto);

        //TODO: Solucionar que la categoria que se le pase pueda ser null
        Optional<Category> category = categoryService.findById(poiDto.getCategoryId());

        if(category.isPresent()){
            newPoi.setCategory(category.get());
        } else{
            throw new SingleEntityNotFoundException(Category.class, poiDto.getCategoryId());
        }

        return save(newPoi);
    }

    public void addPoiToRoute(Poi poi, Route r, List<Poi> steps ,RouteService routeService){

        if(!hasRepeatedPoi(steps, poi)){
            poi.addToRoute(r);
            save(poi);
            routeService.save(r);
        } else{
            throw new RepeatedElementsException(Poi.class);
        }



    }
    public void removePoiFromRoute(Poi poi, Route r, RouteService routeService){

        poi.removeFromRoute(r);
        save(poi);
        routeService.save(r);
    }

    public boolean hasRepeatedPoi(List<Poi> steps, Poi newPoi){

        return steps.contains(newPoi) ? true : false;
    }

}
