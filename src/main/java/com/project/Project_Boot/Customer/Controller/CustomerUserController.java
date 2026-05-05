package com.project.Project_Boot.Customer.Controller;

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

    public CustomerUserController(CustomerService customerService) {
        this.customerService = customerService;
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

}
