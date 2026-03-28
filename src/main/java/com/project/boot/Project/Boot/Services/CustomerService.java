package com.project.boot.Project.Boot.Services;

import com.project.boot.Project.Boot.Entity.Customer;
import com.project.boot.Project.Boot.Repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {


    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public String createAccount(Customer customer){
        if(customerRepository.existsCustomerByUsername(customer.getUsername())){
            return "Username is taken";
        }
        customerRepository.save(customer);
        return "Success";
    }

    public List<Customer> allAccounts(){
        return customerRepository.findAll();
    }
}
