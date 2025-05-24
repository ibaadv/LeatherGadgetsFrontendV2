// ✅ Updated backend with MoneyReturn integration

package com.leathergadgets.LEATHERGADGETSBACKEND;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class MoneyReturn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long returnId;
    private String productName;
    private String buyerMobile;
    private double amount;
    private String status = "Pending";
    private LocalDateTime timestamp = LocalDateTime.now();

    public MoneyReturn(Long id, Long returnId, String productName, String buyerMobile, double amount, String status, LocalDateTime timestamp) {
        this.id = id;
        this.returnId = returnId;
        this.productName = productName;
        this.buyerMobile = buyerMobile;
        this.amount = amount;
        this.status = status;
        this.timestamp = timestamp;
    }

    public MoneyReturn() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReturnId() {
        return returnId;
    }

    public void setReturnId(Long returnId) {
        this.returnId = returnId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBuyerMobile() {
        return buyerMobile;
    }

    public void setBuyerMobile(String buyerMobile) {
        this.buyerMobile = buyerMobile;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
