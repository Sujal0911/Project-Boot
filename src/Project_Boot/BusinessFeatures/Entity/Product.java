package com.project.Project_Boot.BusinessFeatures.Entity;

import com.project.Project_Boot.Enums.ServiceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String city;
    @Column(nullable = false)
    private String duration;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private boolean available;
    @Column(nullable = false)
    private String businessId;
    @Column(nullable = false)
    private String imageUrl;

}
