package com.project.boot.BusinessFeatures.Entity;

import com.project.boot.Enums.ServiceType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ServiceType productType;
    @Column(nullable = false)
    private String duration;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private boolean available;
    @Column(nullable = false)
    private String businessId;

}
