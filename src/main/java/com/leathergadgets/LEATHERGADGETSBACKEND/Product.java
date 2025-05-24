package com.leathergadgets.LEATHERGADGETSBACKEND;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private String shortDescription;
    private Double price;
    private Double discountPrice;
    private Integer quantity;
    private String materialType;
    private String productType;
    private String color;
    private String weight;

    @ElementCollection
    private List<String> sizes;

    @ElementCollection
    @CollectionTable(name = "product_sub_images", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "sub_image", columnDefinition = "LONGTEXT")
    private List<String> subImages;

    @Lob
    @Column(name = "main_image", columnDefinition = "LONGTEXT")
    private String mainImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    @JsonBackReference
    private Seller seller;

    public Product() {
    }

    public Product(Long id, String productName, String shortDescription, Double price, Double discountPrice, Integer quantity, String materialType, String productType, String color, String weight, List<String> sizes, List<String> subImages, String mainImage, Seller seller) {
        this.id = id;
        this.productName = productName;
        this.shortDescription = shortDescription;
        this.price = price;
        this.discountPrice = discountPrice;
        this.quantity = quantity;
        this.materialType = materialType;
        this.productType = productType;
        this.color = color;
        this.weight = weight;
        this.sizes = sizes;
        this.subImages = subImages;
        this.mainImage = mainImage;
        this.seller = seller;
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

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public List<String> getSizes() {
        return sizes;
    }

    public void setSizes(List<String> sizes) {
        this.sizes = sizes;
    }

    public List<String> getSubImages() {
        return subImages;
    }

    public void setSubImages(List<String> subImages) {
        this.subImages = subImages;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
