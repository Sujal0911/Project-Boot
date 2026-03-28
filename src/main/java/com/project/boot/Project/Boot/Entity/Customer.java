package com.project.boot.Project.Boot.Entity;

import jakarta.persistence.Entity;

@Entity
public class Customer extends Account{

    public Customer(String firstName, String lastName, String email, String username, String password, String address, Integer age, Long contactNo) {
        super(firstName, lastName, email, username, password, address, age, contactNo);
    }

    public Customer() {
        super();
    }
}
