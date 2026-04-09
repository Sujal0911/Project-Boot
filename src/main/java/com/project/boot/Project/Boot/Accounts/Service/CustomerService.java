package com.project.boot.Project.Boot.Accounts.Service;

import com.project.boot.Project.Boot.Accounts.Entity.Customer;
import com.project.boot.Project.Boot.Accounts.Repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public Boolean createAccount(Customer customer){
        if(customerRepository.existsCustomerByUserID(customer.getUserID())){
            return false;
        }
        customerRepository.save(customer);
        return true;
    }

    public Customer changeFirstName(Customer customer1){
        if(customer1.getFirstName() == null || customer1.getFirstName().trim() == ""){
            return null;
        }
        Customer customer = customerRepository.findCustomerByUserID(customer1.getUserID());
        if(customer != null){
            customer.setFirstName(customer1.getFirstName().trim());
            customerRepository.save(customer);
            return customer;
        }
        else{
            return null;
        }
    }

    public Customer changeLastName(Customer customer1){
        if(customer1.getLastName() == null || customer1.getLastName().trim() == ""){
            return null;
        }
        Customer customer = customerRepository.findCustomerByUserID(customer1.getUserID());
        if(customer != null){
            customer.setLastName(customer1.getLastName().trim());
            customerRepository.save(customer);
            return customer;
        }
        else{
            return null;
        }
    }

    public Customer changeEmail(Customer customer1){
        if(customer1.getEmail() == null || customer1.getEmail().trim() == ""){
            return null;
        }
        Customer customer = customerRepository.findCustomerByUserID(customer1.getUserID());
        if(customer != null){
            customer.setEmail(customer1.getEmail().trim());
            customerRepository.save(customer);
            return customer;
        }
        else{
            return null;
        }
    }

    public Customer changeAddress(Customer customer1){
        if(customer1.getAddress() == null || customer1.getAddress().trim() == ""){
            return null;
        }
        Customer customer = customerRepository.findCustomerByUserID(customer1.getUserID());
        if(customer != null){
            customer.setAddress(customer1.getAddress().trim());
            customerRepository.save(customer);
            return customer;
        }
        else{
            return null;
        }
    }

    public Customer changeContact(Customer customer1){
        if(customer1.getContactNo() == null){
            return null;
        }
        Customer customer = customerRepository.findCustomerByUserID(customer1.getUserID());
        if(customer != null){
            customer.setContactNo(customer1.getContactNo());
            customerRepository.save(customer);
            return customer;
        }
        else{
            return null;
        }
    }

    public Customer changeAge(Customer customer1){
        if(customer1.getAge() == null || customer1.getAge() < 18){
            return null;
        }
        Customer customer = customerRepository.findCustomerByUserID(customer1.getUserID());
        if(customer != null){
            customer.setAge(customer1.getAge());
            customerRepository.save(customer);
            return customer;
        }
        else{
            return null;
        }
    }

    public List<Customer> allAccounts(){
        return customerRepository.findAll();
    }
}
