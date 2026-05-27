package com.project.Project_Boot.BusinessFeatures.Repository;

import com.project.Project_Boot.BusinessFeatures.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findCartByUserIdAndAvailable(String userId, boolean available);
}
