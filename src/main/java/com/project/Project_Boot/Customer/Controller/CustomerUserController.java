package com.project.Project_Boot.Customer.Controller;

import com.project.Project_Boot.Booking.Service.BookingService;
import com.project.Project_Boot.Customer.Customer;
import com.project.Project_Boot.Customer.CustomerDTO;
import com.project.Project_Boot.Customer.Service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("all-services")
    public ResponseEntity<?> allServices(){
        return ResponseEntity.ok(bookingService.allServices());
    }

}
