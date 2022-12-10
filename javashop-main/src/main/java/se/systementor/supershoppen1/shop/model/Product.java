package se.systementor.supershoppen1.shop.model;

import javax.persistence.*;


@Entity
public class Product {
    private String name;
    private double price;
    private int categoryId;

    public int getCategoryId() {
        return categoryId;
    }
    private int rating;
    private double campaignPercentage;



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
    public String getPrice() {
        return "$"+ Math.round(price);
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

    public String getCampaignPrice() {
        return "$"+ Math.round((price - (price * (campaignPercentage/100))));
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String  getCampaignPercentage() {
        return "-"+Math.round(campaignPercentage) + "%";
    }

    public void setCampaignPercentage(int campaignPercentage) {
        this.campaignPercentage = campaignPercentage;
    }

    
}

