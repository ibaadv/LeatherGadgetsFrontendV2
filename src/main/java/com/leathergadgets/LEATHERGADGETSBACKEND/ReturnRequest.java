// ✅ Updated backend for ReturnRequest to support image + status updates from delivery boy

package com.leathergadgets.LEATHERGADGETSBACKEND;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
public class ReturnRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;
    private String productName;
    private String mainImage;
    private int quantity;
    private double price;
    private String reason;
    private String buyerMobile;
    private String status = "Pending";
    private String deliveryBoyMobile;
    private String photoUrl; // 🆕 New field to store captured image path or base64

    public ReturnRequest(Long id, Long orderId, String productName, String mainImage, int quantity, double price, String reason, String buyerMobile, String status, String deliveryBoyMobile, String photoUrl) {
        this.id = id;
        this.orderId = orderId;
        this.productName = productName;
        this.mainImage = mainImage;
        this.quantity = quantity;
        this.price = price;
        this.reason = reason;
        this.buyerMobile = buyerMobile;
        this.status = status;
        this.deliveryBoyMobile = deliveryBoyMobile;
        this.photoUrl = photoUrl;
    }

    public ReturnRequest() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getMainImage() { return mainImage; }
    public void setMainImage(String mainImage) { this.mainImage = mainImage; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getBuyerMobile() { return buyerMobile; }
    public void setBuyerMobile(String buyerMobile) { this.buyerMobile = buyerMobile; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDeliveryBoyMobile() { return deliveryBoyMobile; }
    public void setDeliveryBoyMobile(String deliveryBoyMobile) { this.deliveryBoyMobile = deliveryBoyMobile; }

    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
}
