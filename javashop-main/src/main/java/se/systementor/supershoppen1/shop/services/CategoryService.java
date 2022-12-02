package se.systementor.supershoppen1.shop.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import se.systementor.supershoppen1.shop.model.Category;
import se.systementor.supershoppen1.shop.model.CategoryRepository;


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

    public Category addCategory (String name, String description) {
        List<Category> existingCategories = getAll();
        for (Category cat : existingCategories) {
            if (cat.getName().equalsIgnoreCase(name)) {
                return null;
            }
        }
        Category newCat = new Category();
        newCat.setName(name);
        newCat.setDescription(description);
        return repository.save(newCat);
    }

    public Category editCategory(Integer id, String name, String description) {
        Category categoryToEdit = repository.findById(id).get();
        categoryToEdit.setName(name);
        categoryToEdit.setDescription(description);
        return repository.save(categoryToEdit);
    }
}

