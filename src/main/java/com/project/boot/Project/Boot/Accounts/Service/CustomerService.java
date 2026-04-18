package com.project.boot.Project.Boot.Accounts.Service;

import com.project.boot.Project.Boot.Accounts.DTO.CustomerDTO;
import com.project.boot.Project.Boot.Accounts.Entity.Customer;
import com.project.boot.Project.Boot.Accounts.Repository.CustomerRepository;
import com.project.boot.Project.Boot.Enums.AccountRole;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder){
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Customer createAdminAccount(Customer customer){
        if(customerRepository.existsCustomerByUserID(customer.getUserID())){
            return null;
        }
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setRole(AccountRole.ADMIN);
        customerRepository.save(customer);
        return customer;
    }

    public Customer createUserAccount(Customer customer){
        if(customerRepository.existsCustomerByUserID(customer.getUserID())){
            return null;
        }
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setRole(AccountRole.USER);
        customerRepository.save(customer);
        return customer;
    }

    public Customer updateCustomer(CustomerDTO request){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Customer customer = customerRepository
                .findCustomerByUserID(auth.getName());

        System.out.println(customer.getUserID() + " " + customer.getPassword());

        if(customer == null){
            return null;
        }

        if(request.getFirstName() != null && !request.getFirstName().trim().isEmpty()){
            customer.setFirstName(request.getFirstName().trim());
        }

        if(request.getLastName() != null && !request.getLastName().trim().isEmpty()){
            customer.setLastName(request.getLastName().trim());
        }

        if(request.getPassword() != null && !request.getPassword().trim().isEmpty()){
            customer.setPassword(passwordEncoder.encode(request.getPassword().trim()));
        }

        if(request.getEmail() != null && !request.getEmail().trim().isEmpty()){
            customer.setEmail(request.getEmail().trim());
        }

        if(request.getAddress() != null && !request.getAddress().trim().isEmpty()){
            customer.setAddress(request.getAddress().trim());
        }

        if(request.getContactNo() != null){
            customer.setContactNo(request.getContactNo());
        }

        if(request.getAge() != null && request.getAge() >= 18){
            customer.setAge(request.getAge());
        }

        return customerRepository.save(customer);
    }

    public List<Customer> allAccounts(){
        return customerRepository.findAll();
    }
}
