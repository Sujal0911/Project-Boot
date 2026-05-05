package com.project.Project_Boot.Customer.Repository;

import com.project.Project_Boot.Customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsCustomerByUserId(String userID);
    Customer findCustomerByUserId(String userID);
}
