package com.example.demo.entity;

import java.time.LocalDateTime;

public class Vendor {
    private long id;
    private String vendorName;
    private String contactEmail;
    private String phone;
    private LocalDateTime createdAt;
    public Vendor(){

    }
    public Vendor(long id, String contactEmail, String phone, LocalDateTime createdAt) {
        this.id = id;
        this.contactEmail = contactEmail;
        this.phone = phone;
        this.createdAt = createdAt;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public long getId() {
        return id;
    }
    public String getVendorName() {
        return vendorName;
    }
    public String getContactEmail() {
        return contactEmail;
    }
    public String getPhone() {
        return phone;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    
    
    
}
