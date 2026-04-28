package com.project.boot.BusinessFeatures.Controller;

import com.project.boot.BusinessFeatures.Entity.Cart;
import com.project.boot.BusinessFeatures.Entity.CartItem;
import com.project.boot.BusinessFeatures.Service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("add-to-cart")
    public ResponseEntity<?> addToCart(@RequestBody List<CartItem> cartItemList){
        Cart cart = cartService.addToCart(cartItemList);
        if(cart == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping("delete-cartItemId/{cartItemId}")
    public ResponseEntity<?> deleteFromCart(@PathVariable Long cartItemId){
        String result = cartService.deleteCartItem(cartItemId);
        if(result == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
