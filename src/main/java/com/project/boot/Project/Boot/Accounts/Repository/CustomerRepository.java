package com.project.boot.Project.Boot.Accounts.Repository;

import com.project.boot.Project.Boot.Accounts.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsCustomerByUserID(String userID);
    Customer findCustomerByUserID(String userID);
}
