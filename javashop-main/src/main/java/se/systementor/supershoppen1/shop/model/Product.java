package se.systementor.supershoppen1.shop.model;

import javax.persistence.*;


@Entity
public class Product {
    private String name;
    private int price;
    private int categoryId;
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    private String filePath;
    private String fileName;

    public int getCategoryId() {
        return categoryId;
    }


    private int rating;
    private int campaignPercentage;



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


    public int getCampaignPrice() {
        return (price - ((price * campaignPercentage)/100));
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getCampaignPercentage() {
        return campaignPercentage;
    }

    public void setCampaignPercentage(int campaignPercentage) {
        this.campaignPercentage = campaignPercentage;
    }


    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public void setCategoryName(String name) {
        this.categoryName = name;
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

