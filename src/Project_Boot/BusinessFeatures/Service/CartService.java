package com.project.Project_Boot.BusinessFeatures.Service;

import com.project.Project_Boot.BusinessFeatures.Entity.Cart;
import com.project.Project_Boot.BusinessFeatures.Entity.CartItem;
import com.project.Project_Boot.BusinessFeatures.Repository.CartRepository;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    public Cart addToCart(Long productId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Cart cart = cartRepository.findCartByUserIdAndAvailable(auth.getName(), true);

        if(cart == null){
            cart = new Cart();
            cart.setAvailable(true);
            cart.setUserId(auth.getName());
        }

            int isPresent = isPresent(cart.getListCart(), productId);
            if(isPresent != -1){
                cart.getListCart().get(isPresent).setQuantity(cart.getListCart().get(isPresent).getQuantity() + 1);
            }
            else{
                CartItem cartItem = new CartItem();
                cartItem.setQuantity(1);
                cartItem.setProductId(productId);
                cart.getListCart().add(cartItem);
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

            if(cart.getListCart().get(mid).getProductId().equals(cartItemID)){
                cart.getListCart().remove(mid);
                break;
            }else if(cart.getListCart().get(mid).getProductId() > cartItemID){
                r = mid - 1;
            }
            else{
                l = mid + 1;
            }
        }

        cartRepository.save(cart);

        return "Deleted";
    }

    public int isPresent(List<CartItem> listCart, Long productId){
        int i = 0;
        while(i < listCart.size()){
            if(listCart.get(i).getId().equals(productId)){
                return i;
            }
            i++;
        }
        return -1;
    }
}