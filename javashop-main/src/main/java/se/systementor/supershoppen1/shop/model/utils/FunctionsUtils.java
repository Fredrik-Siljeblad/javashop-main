package se.systementor.supershoppen1.shop.model.utils;

import org.springframework.context.annotation.Bean;
import se.systementor.supershoppen1.shop.model.Category;
import se.systementor.supershoppen1.shop.model.Product;

import java.util.ArrayList;
import java.util.List;


public class FunctionsUtils {

    public List<CategoryAndProducts> globalCategoryAndProducts(List<Product> productList,List<Category> categoryList){
        List<CategoryAndProducts> list = new ArrayList<>();
        for( Category category: categoryList){
            List<Product> productList1 = new ArrayList<>();
            for(Product product : productList){
                if (product.getCategoryId() == category.getId()){
                    productList1.add(product);
                }
            }
            list.add(new CategoryAndProducts(category,productList1,convertImagePath(category.getFilePath(),category.getFileName())));
        }
        return list;
    }


    public String convertImagePath(String filePath,String fileName){
        if(filePath != null){
            return filePath.substring(filePath.length()-18) +"/"+fileName;
        }
        return "File path string is empty";
    }
}
