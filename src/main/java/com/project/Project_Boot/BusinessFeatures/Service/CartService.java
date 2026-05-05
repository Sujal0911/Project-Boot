package com.project.Project_Boot.BusinessFeatures.Service;

import com.project.Project_Boot.BusinessFeatures.Entity.Cart;
import com.project.Project_Boot.BusinessFeatures.Entity.CartItem;
import com.project.Project_Boot.BusinessFeatures.Repository.CartRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Transactional
    public Cart addToCart(List<CartItem> cartItem) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Cart cart = cartRepository.findCartByUserIdAndAvailable(auth.getName(), true);

        if(cart == null){
            cart = new Cart();
            cart.setAvailable(true);
            cart.setUserId(auth.getName());
        }

        for(CartItem c : cartItem){
            cart.getListCart().add(c);
        }

        cartRepository.save(cart);

        return cart;
    }

    @Transactional
    public String deleteCartItem(Long cartItemID){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Cart cart = cartRepository.findCartByUserIdAndAvailable(auth.getName(), true);
        if(cart == null){
            return null;
        }

//        int ind = 0;
//        for(CartItem c : cart.getListCart()){
//            if(c.getCartItemId().equals(cartItemID)){
//                break;
//            }
//            ind++;
//        }
//        if(ind == cart.getListCart().size()){
//            return null;
//        }
//        cart.getListCart().remove(ind);

        int l = 0, r = cart.getListCart().size() - 1;
        while(l <= r){
            int mid = l + (r - l) / 2;

            if(cart.getListCart().get(mid).getCartItemId().equals(cartItemID)){
                cart.getListCart().remove(mid);
                break;
            }else if(cart.getListCart().get(mid).getCartItemId() > cartItemID){
                r = mid - 1;
            }
            else{
                l = mid + 1;
            }
        }

        cartRepository.save(cart);

        return "Deleted";
    }
}