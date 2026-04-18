package com.project.boot.Project.Boot.Accounts.Controller;

import com.project.boot.Project.Boot.Accounts.DTO.CustomerDTO;
import com.project.boot.Project.Boot.Accounts.Entity.Customer;
import com.project.boot.Project.Boot.Accounts.Service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer-admin")
public class CustomerAdminController {

    private final CustomerService customerService;

    public CustomerAdminController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("list")
    public List<Customer> allAccounts(){
        return customerService.allAccounts();
    }

    @PostMapping("create-account")
    public ResponseEntity<?> createAdminAccount(@RequestBody Customer customer){
        Customer customer1 = customerService.createAdminAccount(customer);
        if(customer1 != null){
            return new ResponseEntity<>(customer1, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }

    @PatchMapping("update")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerDTO request){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        Customer updatedCustomer = customerService.updateCustomer(request);

        if(updatedCustomer != null){
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        }

        return new ResponseEntity<>("Customer Not Found", HttpStatus.NOT_FOUND);
    }

}