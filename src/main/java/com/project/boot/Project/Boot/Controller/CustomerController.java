package com.project.boot.Project.Boot.Controller;
import com.project.boot.Project.Boot.Entity.Customer;
import com.project.boot.Project.Boot.Services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("create")
    public String createAccount(@RequestBody Customer customer){
        return customerService.createAccount(customer);
    }

    @GetMapping("list")
    public List<Customer> allAccounts(){
        return customerService.allAccounts();
    }
}
