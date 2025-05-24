package com.leathergadgets.LEATHERGADGETSBACKEND;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private Double price;

    @Lob
    @Column(name = "main_image", columnDefinition = "LONGTEXT")
    private String mainImage;

    private String deliveryAddress;
    private String pinCode;
    private String buyerMobile;

    @Lob
    @Column(name = "sellerqrcode", columnDefinition = "LONGTEXT")
    private String sellerQRCode;

    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @ManyToOne
    private DeliveryPerson assignedDeliveryBoy;

    public Order() {
    }

    public Order(Long id, String productName, Double price, String mainImage, String deliveryAddress, String pinCode, String buyerMobile, String sellerQRCode, String status, Seller seller, DeliveryPerson assignedDeliveryBoy) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.mainImage = mainImage;
        this.deliveryAddress = deliveryAddress;
        this.pinCode = pinCode;
        this.buyerMobile = buyerMobile;
        this.sellerQRCode = sellerQRCode;
        this.status = status;
        this.seller = seller;
        this.assignedDeliveryBoy = assignedDeliveryBoy;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getBuyerMobile() {
        return buyerMobile;
    }

    public void setBuyerMobile(String buyerMobile) {
        this.buyerMobile = buyerMobile;
    }

    public String getSellerQRCode() {
        return sellerQRCode;
    }

    public void setSellerQRCode(String sellerQRCode) {
        this.sellerQRCode = sellerQRCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public DeliveryPerson getAssignedDeliveryBoy() {
        return assignedDeliveryBoy;
    }

    public void setAssignedDeliveryBoy(DeliveryPerson assignedDeliveryBoy) {
        this.assignedDeliveryBoy = assignedDeliveryBoy;
    }
}