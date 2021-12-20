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

import java.util.ArrayList;
import java.util.Iterator;
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

    public List<Poi> findAllByCategory(Long id){
        return repository.findAllByCategory(id);
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

    public Poi edit(Poi poi, CreatePoiDto poiDto, CategoryService categoryService){

        poi = Poi.builder()
                .id(poi.getId())
                .name(poiDto.getName())
                .location(poiDto.getLocation())
                .date(poiDto.getDate())
                .photo2(poiDto.getPhoto2())
                .photo3(poiDto.getPhoto3())
                .coverPhoto(poiDto.getCoverPhoto())
                .description(poiDto.getDescription())
                .category(categoryService.findCategoryById(poiDto.getCategoryId()))
                .build();

        return save(poi);

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

    public void deletePoi(Poi poi, RouteService routeService){

        List<Route> listaBase = new ArrayList<>();
        listaBase.addAll(poi.getRoutes());
        for (Iterator<Route> it = listaBase.iterator(); it.hasNext();){
            Route r = it.next();
            removePoiFromRoute(poi, r, routeService);
        }

        delete(poi);

    }

    public boolean hasRepeatedPoi(List<Poi> steps, Poi newPoi){

        return steps.contains(newPoi) ? true : false;
    }

}
