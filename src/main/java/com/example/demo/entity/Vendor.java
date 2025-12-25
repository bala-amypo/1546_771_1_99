package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "vendorName"))
public class Vendor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String vendorName;

    @Email
    private String contactEmail;

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getVendorName() { return vendorName; }
    public void setVendorName(String vendorName) { this.vendorName = vendorName; }
    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
}