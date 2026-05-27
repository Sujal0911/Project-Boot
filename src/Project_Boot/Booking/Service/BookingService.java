package com.project.Project_Boot.Booking.Service;

import com.project.Project_Boot.Booking.Booking;
import com.project.Project_Boot.Booking.Repository.BookingRepository;
import com.project.Project_Boot.Business.Repository.BusinessRepository;
import com.project.Project_Boot.Business.Service.BusinessService;
import com.project.Project_Boot.BusinessFeatures.Entity.Cart;
import com.project.Project_Boot.BusinessFeatures.Entity.CartItem;
import com.project.Project_Boot.BusinessFeatures.Entity.Product;
import com.project.Project_Boot.BusinessFeatures.Repository.CartRepository;
import com.project.Project_Boot.BusinessFeatures.Repository.ProductRepository;
import com.project.Project_Boot.Customer.Repository.CustomerRepository;
import com.project.Project_Boot.Enums.ServiceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        if(cart.getListCart().isEmpty()){
            return null;
        }
        for(CartItem c : cart.getListCart()){
            Product product = productRepository.findProductById(c.getProductId());
            total += product.getPrice() * c.getQuantity();
        }

        return total;
    }

    @Transactional
    public Booking seeBooking(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Booking booking = new Booking();
        Cart cart = cartRepository.findCartByUserIdAndAvailable(auth.getName(), true);
        booking.setTotal(total(cart));
        for(int i = 0; i < cart.getListCart().size(); i++){
            Product product = productRepository.findProductById(cart.getListCart().get(i).getProductId());
            if(product != null) {
                booking.getProducts().add(product);
            }
        }

        return booking;
    }

    public Page<Product> allHotels(Pageable page){
        return productRepository.findProductsByProductTypeEquals(ServiceType.Hotels, page);
    }

    public Page<Product> allGuestHouse(Pageable page){
        return productRepository.findProductsByProductTypeEquals(ServiceType.GuestHouse, page);
    }

    public Page<Product> allServices(Pageable page){
        return productRepository.findProductsByProductTypeEquals(ServiceType.Services, page);
    }

    public Page<Product> allExperiences(Pageable page){
        return productRepository.findProductsByProductTypeEquals(ServiceType.Experiences, page);
    }

    public Page<Product> searchHotels(String city, Pageable page){
        return productRepository.findProductsByProductTypeAndCityContainingIgnoreCase(ServiceType.Hotels,city, page);
    }

    public Page<Product> searchGuestHouse(String city, Pageable page){
        return productRepository.findProductsByProductTypeAndCityContainingIgnoreCase(ServiceType.GuestHouse,city, page);
    }

    public Page<Product> searchServices(String city, Pageable page){
        return productRepository.findProductsByProductTypeAndCityContainingIgnoreCase(ServiceType.Services,city, page);
    }

    public Page<Product> searchExperiences(String city, Pageable page){
        return productRepository.findProductsByProductTypeAndCityContainingIgnoreCase(ServiceType.Experiences,city, page);
    }

    //Stays

    public Page<Product> allStays(Pageable page){
        return productRepository.findProductsByProductTypeEqualsOrProductTypeEquals(ServiceType.Hotels, ServiceType.GuestHouse, page);
    }

    public Page<Product> searchStays(String city, Pageable page){
        return productRepository.findByProductTypeInAndCityContainingIgnoreCase(List.of(ServiceType.Hotels, ServiceType.GuestHouse), city, page);
    }
}
