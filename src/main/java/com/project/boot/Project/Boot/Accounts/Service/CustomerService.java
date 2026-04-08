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
        Customer customer = customerRepository.findCustomerByUserID(customer1.getUserID());
        if(customer != null){
            customer.setFirstName(customer1.getFirstName());
            customerRepository.save(customer);
            return customer;
        }
        else{
            return null;
        }
    }

    public Customer changeLastName(Customer customer1){
        Customer customer = customerRepository.findCustomerByUserID(customer1.getUserID());
        if(customer != null){
            customer.setLastName(customer1.getFirstName());
            customerRepository.save(customer);
            return customer;
        }
        else{
            return null;
        }
    }

    public Customer changeEmail(Customer customer1){
        Customer customer = customerRepository.findCustomerByUserID(customer1.getUserID());
        if(customer != null){
            customer.setEmail(customer1.getFirstName());
            customerRepository.save(customer);
            return customer;
        }
        else{
            return null;
        }
    }

    public Customer changeAddress(Customer customer1){
        Customer customer = customerRepository.findCustomerByUserID(customer1.getUserID());
        if(customer != null){
            customer.setAddress(customer1.getFirstName());
            customerRepository.save(customer);
            return customer;
        }
        else{
            return null;
        }
    }

    public Customer changeContact(Customer customer1){
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
