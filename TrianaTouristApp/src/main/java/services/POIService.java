package services;

import models.poi.POI;
import org.springframework.stereotype.Service;
import repositories.POIRepository;
import services.base.BaseService;

@Service
public class POIService extends BaseService<POI, Long, POIRepository> {
}
