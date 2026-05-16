package com.project.Project_Boot.Customer.Service;

import com.project.Project_Boot.Business.Repository.BusinessRepository;
import com.project.Project_Boot.BusinessFeatures.Entity.Cart;
import com.project.Project_Boot.BusinessFeatures.Entity.CartItem;
import com.project.Project_Boot.BusinessFeatures.Repository.CartRepository;
import com.project.Project_Boot.BusinessFeatures.Repository.ProductRepository;
import com.project.Project_Boot.Customer.Customer;
import com.project.Project_Boot.Customer.CustomerDTO;
import com.project.Project_Boot.Customer.Repository.CustomerRepository;
import com.project.Project_Boot.Enums.AccountRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final BusinessRepository businessRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder, BusinessRepository businessRepository, CartRepository cartRepository, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.businessRepository = businessRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
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

    @Transactional
    public Boolean cartItemToOrderHistory(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = customerRepository.findCustomerByUserId(auth.getName());
        Cart cart = cartRepository.findCartByUserIdAndAvailable(auth.getName(), true);
        for(CartItem c : cart.getListCart()) {
            customer.getOrders().add(c);
        }
        customerRepository.save(customer);
        cart.setListCart(null);
        cartRepository.save(cart);

        return true;
    }

    @Transactional
    public List<CartItem> pastOrders() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = customerRepository.findCustomerByUserId(auth.getName());
        return customer.getOrders();
    }
}