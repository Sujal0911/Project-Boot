package com.project.boot.Project.Boot.Accounts.Service;

import com.project.boot.Project.Boot.Accounts.Entity.Customer;
import com.project.boot.Project.Boot.SecurityModel.CustomerUserDetails;
import com.project.boot.Project.Boot.Accounts.Repository.CustomerRepository;
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
        Customer customer = customerRepository.findCustomerByUserID(username);

        if (customer == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new CustomerUserDetails(customer);
    }
}
