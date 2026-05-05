package com.project.Project_Boot.BusinessFeatures.DTO;

import com.project.Project_Boot.Enums.ServiceType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

    private Long id;
    private double price;
    private String ProductName;
    private ServiceType ProductType;
    private String duration;
    private String description;
    private String businessId;
    private boolean available;
}