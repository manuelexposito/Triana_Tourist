package services;

import models.category.Category;
import org.springframework.stereotype.Service;
import repositories.CategoryRepository;
import services.base.BaseService;

@Service
public class CategoryService extends BaseService<Category, Long,CategoryRepository> {
}
