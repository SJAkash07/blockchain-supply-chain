package com.blockchain.supplychain.model;

public class Product {
    private int productId;
    private String name;
    private String category;
    private String description;

    public Product(int productId, String name, String category, String description) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.description = description;
    }

    public int getProductId() { return productId; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
}
