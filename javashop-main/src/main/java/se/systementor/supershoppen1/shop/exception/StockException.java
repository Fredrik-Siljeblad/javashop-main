package se.systementor.supershoppen1.shop.exception;

import se.systementor.supershoppen1.shop.model.Product;

public class StockException extends Exception {
    private static final String DEFAULT_MESSAGE = "Not enough products in stock";

    public StockException() {
        super(DEFAULT_MESSAGE);
    }

    public StockException(Product product) {
        super((String.format("Not enough %s products in stock. Only %d left", product.getName(), product.getStockLevel())));
    }

}
