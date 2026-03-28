package com.project.boot.Project.Boot.Repository;

import com.project.boot.Project.Boot.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    boolean existsCustomerByUsername(String username);
}
