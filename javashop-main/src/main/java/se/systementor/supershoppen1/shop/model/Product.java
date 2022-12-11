package se.systementor.supershoppen1.shop.model;

import javax.persistence.*;


@Entity
public class Product {
    private String name;
    private int price;
    private int categoryId;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private int stockLevel;
    private String description;

    public Integer getId() {
        return id;
      }

      public void setId(Integer id) {
        this.id = id;
      }

      public void setName(String v)
      {
          name = v;
      }


      public String getName()
      {
          return name;
      }

    public void setPrice(int d) {
        price = d;
    }
    public int getPrice() {
        return price;
    }

    //Both set/get Category/CategoryId do the same thing, but set/getCategory messed things up with the db.
    public void setCategoryId(int d) {
        categoryId = d;
    }
    public int getCategoryId() {
        return categoryId;
    }
    public void setCategory(int d) {
        categoryId = d;
    }
    public int getCategory() {
        return categoryId;
    }


    public void setStockLevel(int i) {
        stockLevel = i;
    }

    public int getStockLevel() {
        return stockLevel;
    }


    public void setDescription(String string) {
        description = string;
    }
    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
    
}

