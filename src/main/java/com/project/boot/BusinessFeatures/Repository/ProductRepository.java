package com.project.boot.BusinessFeatures.Repository;

import com.project.boot.BusinessFeatures.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductById(Long Id);
    List<Product> findProductsByBusinessId(String userID);

    void deleteProductById(Long Id);
}
