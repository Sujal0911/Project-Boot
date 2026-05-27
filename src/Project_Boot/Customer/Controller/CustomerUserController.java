package com.project.Project_Boot.Customer.Controller;

import com.project.Project_Boot.Booking.Service.BookingService;
import com.project.Project_Boot.Customer.Customer;
import com.project.Project_Boot.Customer.CustomerDTO;
import com.project.Project_Boot.Customer.Service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import java.awt.*;

@RestController
@RequestMapping("customer-user")
public class CustomerUserController {

    private final CustomerService customerService;
    private final BookingService bookingService;

    public CustomerUserController(CustomerService customerService, BookingService bookingService) {
        this.customerService = customerService;
        this.bookingService = bookingService;
    }

    @PostMapping("create-account")
    public ResponseEntity<?> createUserAccount(@RequestBody Customer customer){
        Customer customer1 = customerService.createUserAccount(customer);
        if(customer1 != null){
            return new ResponseEntity<>(customer1, HttpStatus.OK);
        }
        return new ResponseEntity<>("Exist", HttpStatus.CONFLICT);
    }

    @PatchMapping("update")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerDTO request){

        Customer updatedCustomer = customerService.updateCustomer(request);

        if(updatedCustomer != null){
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        }

        return new ResponseEntity<>("Customer Not Found",HttpStatus.NOT_FOUND);
    }

    @GetMapping("order-history")
    public ResponseEntity<?> cartToOrderHistory(){
        return new ResponseEntity<>(customerService.cartItemToOrderHistory(), HttpStatus.OK);
    }

    @GetMapping("past-orders")
    public ResponseEntity<?> pastOrders(){
        return ResponseEntity.ok(customerService.pastOrders());
    }

    @GetMapping("all-hotels")
    public ResponseEntity<?> allHotels(Pageable page){
        return ResponseEntity.ok(bookingService.allHotels(page));
    }

    @GetMapping("all-guestHouse")
    public ResponseEntity<?> allGuestHouse(Pageable page){
        return ResponseEntity.ok(bookingService.allGuestHouse(page));
    }

    @GetMapping("all-services")
    public ResponseEntity<?> allServices(Pageable page){
        return ResponseEntity.ok(bookingService.allServices(page));
    }

    @GetMapping("all-experiences")
    public ResponseEntity<?> allExperiences(Pageable page){
        return ResponseEntity.ok(bookingService.allExperiences(page));
    }


    @GetMapping("search-hotels")
    public ResponseEntity<?> searchHotels(@RequestParam String city, Pageable page){
        return ResponseEntity.ok(bookingService.searchHotels(city, page));
    }

    @GetMapping("search-guestHouse")
    public ResponseEntity<?> searchGuestHouse(@RequestParam String city, Pageable page){
        return ResponseEntity.ok(bookingService.searchGuestHouse(city, page));
    }

    @GetMapping("search-services")
    public ResponseEntity<?> searchServices(@RequestParam String city, Pageable page){
        return ResponseEntity.ok(bookingService.searchServices(city, page));
    }

    @GetMapping("search-experiences")
    public ResponseEntity<?> searchExperiences(@RequestParam String city, Pageable page){
        return ResponseEntity.ok(bookingService.searchExperiences(city, page));
    }

    //stays

    @GetMapping("all-stays")
    public ResponseEntity<?> allStays(Pageable page){
        return ResponseEntity.ok(bookingService.allStays(page));
    }

    @GetMapping("search-stays")
    public ResponseEntity<?> searchStays(String city, Pageable page){
        return ResponseEntity.ok(bookingService.searchStays(city, page));
    }

}
