package com.leathergadgets.LEATHERGADGETSBACKEND;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "delivery_persons")
public class DeliveryPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String mobile;
    private String email;
    private String vehicleType;
    private String vehicleNumber;
    private String area;
    private String pinCode;
    private String profileImageUrl; // Stored path or filename

    public DeliveryPerson() {}

    public DeliveryPerson(String name, String mobile, String email,
                          String vehicleType, String vehicleNumber,
                          String area, String pinCode, String profileImageUrl) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.vehicleType = vehicleType;
        this.vehicleNumber = vehicleNumber;
        this.area = area;
        this.pinCode = pinCode;
        this.profileImageUrl = profileImageUrl;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }

    public String getVehicleNumber() { return vehicleNumber; }
    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    public String getPinCode() { return pinCode; }
    public void setPinCode(String pinCode) { this.pinCode = pinCode; }

    public String getProfileImageUrl() { return profileImageUrl; }
    public void setProfileImageUrl(String profileImageUrl) { this.profileImageUrl = profileImageUrl; }
}
