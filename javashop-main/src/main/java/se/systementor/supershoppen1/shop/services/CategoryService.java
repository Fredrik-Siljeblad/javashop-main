package se.systementor.supershoppen1.shop.services;

import java.util.ArrayList;
import java.util.List;
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

    public String getName(Integer id){
        return repository.findById(id).get().getName();
    }

    public void save(Category product1) {
        repository.save(product1);
    }

    public Category addCategory (Category categoryToAdd) {
        List<Category> existingCategories = getAll();
        for (Category cat : existingCategories) {
            if (cat.getName().equalsIgnoreCase(categoryToAdd.getName())) {
                return null;
            }
        }
        return repository.save(categoryToAdd);
    }

    public Category editCategory(Integer id, String name, String description) {
        Category categoryToEdit = repository.findById(id).get();
        categoryToEdit.setName(name);
        categoryToEdit.setDescription(description);
        return repository.save(categoryToEdit);
    }
}

