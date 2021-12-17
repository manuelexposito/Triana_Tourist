package com.salesianostriana.trianatouristapp.services;

import com.salesianostriana.trianatouristapp.models.route.Route;
import org.springframework.stereotype.Service;
import com.salesianostriana.trianatouristapp.repositories.RouteRepository;
import com.salesianostriana.trianatouristapp.services.base.BaseService;

@Service
public class RouteService extends BaseService<Route, Long, RouteRepository> {
}
