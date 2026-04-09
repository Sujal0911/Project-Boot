package com.project.boot.Project.Boot.Accounts.Controller;
import com.project.boot.Project.Boot.Accounts.Entity.Customer;
import com.project.boot.Project.Boot.Accounts.Service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("createAccount")
    public ResponseEntity<?> createAccount(@RequestBody Customer customer){
        if(customerService.createAccount(customer)){
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }

    @PutMapping("changeFirstName")
    public ResponseEntity<?> changeFirstName(@RequestBody Customer customer1){
        Customer customer = customerService.changeFirstName(customer1);
        if(customer != null){
            return new ResponseEntity<>(customer.getFirstName(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("changeLastName")
    public ResponseEntity<?> changeLastName(@RequestBody Customer customer1){
        Customer customer = customerService.changeLastName(customer1);
        if(customer != null){
            return new ResponseEntity<>(customer.getLastName(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("changeEmail")
    public ResponseEntity<?> changeEmail(@RequestBody Customer customer1){
        Customer customer = customerService.changeEmail(customer1);
        if(customer != null){
            return new ResponseEntity<>(customer.getEmail(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("changeAddress")
    public ResponseEntity<?> changeAddress(@RequestBody Customer customer1){
        Customer customer = customerService.changeAddress(customer1);
        if(customer != null){
            return new ResponseEntity<>(customer.getAddress(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("changeContact")
    public ResponseEntity<?> changeContact(@RequestBody Customer customer1){
        Customer customer = customerService.changeContact(customer1);
        if(customer != null){
            return new ResponseEntity<>(customer.getContactNo(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("changeAge")
    public ResponseEntity<?> changeAge(@RequestBody Customer customer1){
        Customer customer = customerService.changeAge(customer1);
        if(customer != null){
            return new ResponseEntity<>(customer.getAge(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("list")
    public List<Customer> allAccounts(){
        return customerService.allAccounts();
    }
}
