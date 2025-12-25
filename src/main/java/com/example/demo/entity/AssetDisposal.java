package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssetDisposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double disposalValue;     // getDisposalValue()

    private LocalDateTime createdAt;  // setCreatedAt()

    @ManyToOne
    private Asset asset;              // setAsset(), getAsset()

    @ManyToOne
    private User approvedBy;          // setApprovedBy()
}
    