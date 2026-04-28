package com.project.boot.Customer.Security;

import com.project.boot.Customer.Customer;
import com.project.boot.Customer.Repository.CustomerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public CustomerDetailService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findCustomerByUserId(username);

        if (customer == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new CustomerUserDetails(customer);
    }
}
