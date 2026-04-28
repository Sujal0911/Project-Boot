package com.project.boot.BusinessFeatures.Repository;

import com.project.boot.BusinessFeatures.Entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.boot.BusinessFeatures.Entity.Cart;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findCartByUserIdAndAvailable(String userId, boolean available);
}
