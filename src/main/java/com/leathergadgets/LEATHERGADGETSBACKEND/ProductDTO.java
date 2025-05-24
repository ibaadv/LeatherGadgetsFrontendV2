package com.leathergadgets.LEATHERGADGETSBACKEND;

public class ProductDTO {
    private Long id;
    private String productName;
    private String mainImage;
    private Double price;

    public ProductDTO(Long id, String productName, String mainImage, Double price) {
        this.id = id;
        this.productName = productName;
        this.mainImage = mainImage;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
