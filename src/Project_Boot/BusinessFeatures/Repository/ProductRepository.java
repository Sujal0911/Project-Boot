package com.project.Project_Boot.BusinessFeatures.Repository;

import com.project.Project_Boot.BusinessFeatures.Entity.Product;
import com.project.Project_Boot.Enums.ServiceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductById(Long Id);
    List<Product> findProductsByBusinessId(String userID);

    Page<Product> findProductsByProductTypeEquals(ServiceType serviceType, Pageable pageable);

    Page<Product> findProductsByProductTypeAndCityContainingIgnoreCase(ServiceType productType, String city, Pageable pageable);

    Page<Product> findProductsByProductTypeEqualsOrProductTypeEquals(ServiceType productType, ServiceType productType1, Pageable pageable);

    Page<Product> findByProductTypeInAndCityContainingIgnoreCase(List<ServiceType> types, String city, Pageable pageable);

    void deleteProductById(Long Id);
}
