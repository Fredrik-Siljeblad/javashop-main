package se.systementor.supershoppen1.shop.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import se.systementor.supershoppen1.shop.model.Category;
import se.systementor.supershoppen1.shop.model.CategoryRepository;
import se.systementor.supershoppen1.shop.model.utils.FileUploadUtil;


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
        if(repository.findById(id).isPresent()) {
            return repository.findById(id).get();
        }
        return null;
    }

    public String getName(Integer id){
        if (repository.findById(id).isPresent()) {
            return repository.findById(id).get().getName();
        }
        return null;
    }

    public void save(Category product1) {
        repository.save(product1);
    }

    public Category addCategory (Category categoryToAdd, MultipartFile multipartFile) throws IOException {
        String uploadDir = "src/main/resources/static/images/Categories";
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        categoryToAdd.setFilePath(uploadDir);
        categoryToAdd.setFileName(fileName);
        FileUploadUtil.saveFile(uploadDir,fileName,multipartFile);
        List<Category> existingCategories = getAll();
        for (Category cat : existingCategories) {
            if (cat.getName().equalsIgnoreCase(categoryToAdd.getName())) {
                return null;
            }
        }
        return repository.save(categoryToAdd);
    }

    public Category editCategory(Integer id, Category category, MultipartFile multipartFile) throws IOException {
        String uploadDir = "src/main/resources/static/images/Categories";
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        FileUploadUtil.saveFile(uploadDir,fileName,multipartFile);
        Category categoryToEdit = repository.findById(id).get();
        categoryToEdit.setName(category.getName());
        categoryToEdit.setDescription(category.getDescription());
        categoryToEdit.setFileName(fileName);
        categoryToEdit.setFilePath(uploadDir);
        return repository.save(categoryToEdit);
    }
}

