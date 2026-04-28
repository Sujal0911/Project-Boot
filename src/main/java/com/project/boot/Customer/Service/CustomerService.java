package com.project.boot.Customer.Service;

import com.project.boot.Business.Repository.BusinessRepository;
import com.project.boot.Customer.Customer;
import com.project.boot.Customer.CustomerDTO;
import com.project.boot.Customer.Repository.CustomerRepository;
import com.project.boot.Enums.AccountRole;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final BusinessRepository businessRepository;

    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder, BusinessRepository businessRepository){
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.businessRepository = businessRepository;
    }

    public Customer createAdminAccount(Customer customer){
        if(customerRepository.existsCustomerByUserId(customer.getUserId()) || businessRepository.existsBusinessByUserId(customer.getUserId())){
            return null;
        }
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setRole(AccountRole.ADMIN);
        customerRepository.save(customer);
        return customer;
    }

    public Customer createUserAccount(Customer customer){
        if(customerRepository.existsCustomerByUserId(customer.getUserId()) || businessRepository.existsBusinessByUserId(customer.getUserId())){
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
                .findCustomerByUserId(auth.getName());

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
