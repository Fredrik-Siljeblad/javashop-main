package se.systementor.supershoppen1.shop.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import se.systementor.supershoppen1.shop.model.Category;
import se.systementor.supershoppen1.shop.model.CategoryRepository;
import se.systementor.supershoppen1.shop.model.Product;
import se.systementor.supershoppen1.shop.model.ProductRepository;
import se.systementor.supershoppen1.shop.SeedData;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    CategoryService(CategoryRepository rep) {
        super();
        this.repository = rep;
    }

    public List<Category> getAll(){
        var l = new ArrayList<Category>();
        for(Category r : repository.findAll())
        {
            l.add(r);
        }
        return l;
    }

    public Category get(Integer id){
        return repository.findById(id).get();
    }

    public void save(Category product1) {
        repository.save(product1);
    }

    public void addCategory (String name, String description) {
        List<Category> existingCategories = getAll();
        for (Category cat : existingCategories) {
            if (cat.getName().equalsIgnoreCase(name)) {
                return;
            }
            Category newCat = new Category();
            newCat.setName(name);
            newCat.setDescription(description);
            save(newCat);
        }
    }
}

