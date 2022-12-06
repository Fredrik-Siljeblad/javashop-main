package se.systementor.supershoppen1.shop.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {


    List<Product> findProductByCategoryId(Integer id);


}
