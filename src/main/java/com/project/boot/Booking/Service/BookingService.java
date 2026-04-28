package com.project.boot.Booking.Service;

import com.project.boot.Booking.Booking;
import com.project.boot.Booking.Repository.BookingRepository;
import com.project.boot.Business.Repository.BusinessRepository;
import com.project.boot.Business.Service.BusinessService;
import com.project.boot.BusinessFeatures.Entity.Cart;
import com.project.boot.BusinessFeatures.Entity.CartItem;
import com.project.boot.BusinessFeatures.Entity.Product;
import com.project.boot.BusinessFeatures.Repository.CartRepository;
import com.project.boot.BusinessFeatures.Repository.ProductRepository;
import com.project.boot.Customer.Repository.CustomerRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BusinessRepository businessRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final BusinessService businessService;
    private final CartRepository cartRepository;

    public BookingService(BookingRepository bookingRepository, BusinessRepository businessRepository, CustomerRepository customerRepository, ProductRepository productRepository, BusinessService businessService, CartRepository cartRepository) {
        this.bookingRepository = bookingRepository;
        this.businessRepository = businessRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.businessService = businessService;
        this.cartRepository = cartRepository;
    }

    public Double total(Cart cart){

        double total = 0;
        for(CartItem c : cart.getListCart()){
            Product product = productRepository.findProductById(c.getProductId());
            total += product.getPrice() * c.getQuantity();
        }

        return total;
    }

    @Transactional
    public double seeBooking(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Booking booking = new Booking();
        booking.setCart(cartRepository.findCartByUserIdAndAvailable(auth.getName(), true));

        return total(booking.getCart());
    }
}
