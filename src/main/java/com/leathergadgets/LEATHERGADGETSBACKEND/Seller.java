package com.leathergadgets.LEATHERGADGETSBACKEND;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.List;

@Entity
@Table(name = "sellers")
//@Builder
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String storeName;
    private String ownerName;
    private String email;
    private String mobile;
    private String gst;
    private String pan;
    private String address;
    private String pincode;

    private String accountNumber;
    private String ifscCode;
    private String upiId;
    private String qrCodeImage;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference  // ✅ Prevents recursion from parent -> child -> parent loop
    @JsonIgnore // 👈 This hides products when returning Seller in API
    private List<Product> products;

    public Seller() {}

    public Seller(String storeName, String ownerName, String email, String mobile,
                  String gst, String pan, String address, String pincode,
                  String accountNumber, String ifscCode, String upiId, String qrCodeImage) {
        this.storeName = storeName;
        this.ownerName = ownerName;
        this.email = email;
        this.mobile = mobile;
        this.gst = gst;
        this.pan = pan;
        this.address = address;
        this.pincode = pincode;
        this.accountNumber = accountNumber;
        this.ifscCode = ifscCode;
        this.upiId = upiId;
        this.qrCodeImage = qrCodeImage;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStoreName() { return storeName; }
    public void setStoreName(String storeName) { this.storeName = storeName; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getGst() { return gst; }
    public void setGst(String gst) { this.gst = gst; }

    public String getPan() { return pan; }
    public void setPan(String pan) { this.pan = pan; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getIfscCode() { return ifscCode; }
    public void setIfscCode(String ifscCode) { this.ifscCode = ifscCode; }

    public String getUpiId() { return upiId; }
    public void setUpiId(String upiId) { this.upiId = upiId; }

    public String getQrCodeImage() { return qrCodeImage; }
    public void setQrCodeImage(String qrCodeImage) { this.qrCodeImage = qrCodeImage; }

    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }
}
