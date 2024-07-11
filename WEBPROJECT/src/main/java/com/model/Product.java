package com.model;

import java.math.BigDecimal;

public class Product {
    private int productId;
    private String productName;
    private String productDescription;
    private BigDecimal price;
    private BigDecimal weight;
    private String category;

    public Product() {
        // Default constructor
    }

    public Product(String productName, String productDescription, BigDecimal price, BigDecimal weight, String category) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
        this.weight = weight;
        this.category = category;
    }

    // Getters and setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // toString method (optional for debugging)
    @Override
    public String toString() {
        return "Product [productId=" + productId + ", productName=" + productName + ", productDescription="
                + productDescription + ", price=" + price + ", weight=" + weight + ", category=" + category + "]";
    }
}