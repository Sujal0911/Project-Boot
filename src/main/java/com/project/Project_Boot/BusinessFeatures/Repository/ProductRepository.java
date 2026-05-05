package com.project.Project_Boot.BusinessFeatures.Repository;

import com.project.Project_Boot.BusinessFeatures.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductById(Long Id);
    List<Product> findProductsByBusinessId(String userID);

    void deleteProductById(Long Id);
}
