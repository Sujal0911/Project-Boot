package com.project.Project_Boot.Customer.Security;

import com.project.Project_Boot.Customer.Customer;
import com.project.Project_Boot.Customer.Repository.CustomerRepository;
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
