package com.project.boot.Project.Boot.BusinessFeatures.Entity;

import jakarta.persistence.*;

@Entity
public class Services {

    @Id
    private Long serviceId;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private String serviceName;
    @Column(nullable = false)
    private String serviceType;
    @Column(nullable = false)
    private String duration;
    @Column(nullable = false)
    private String description;

    public Services() {
    }

    public Services(Long serviceId, double price, String serviceName, String serviceType, String duration, String description) {
        this.serviceId = serviceId;
        this.price = price;
        this.serviceName = serviceName;
        this.serviceType = serviceType;
        this.duration = duration;
        this.description = description;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
