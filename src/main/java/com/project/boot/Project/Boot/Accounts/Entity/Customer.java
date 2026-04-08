package com.project.boot.Project.Boot.Accounts.Entity;

import jakarta.persistence.Entity;

@Entity
public class Customer extends Account {

    public Customer(String userID, String password, String firstName, String lastName, Integer age, String email, String address, Long contactNo) {
        super(userID, password, firstName, lastName, age, email, address, contactNo);
    }

    public Customer() {
        super();
    }

}
