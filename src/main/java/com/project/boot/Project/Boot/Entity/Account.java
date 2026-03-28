package com.project.boot.Project.Boot.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Account {
    @Column(nullable = false)
    String firstName;
    String lastName;
    @Column(nullable = false)
    String email;
    @Id
    String username;
    @Column(nullable = false)
    String password;
    @Column(nullable = false)
    String address;
    @Column(nullable = false)
    Integer age;
    @Column(nullable = false)
    Long contactNo;

    public Account() {

    }

    public Account(String first_name, String last_name, String email, String username, String password, String address, Integer age, Long contact_no) {
        this.firstName = first_name;
        this.lastName = last_name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.address = address;
        this.age = age;
        this.contactNo = contact_no;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        if(age < 18){
            throw new IllegalArgumentException("Must be 18");
        }
        this.age = age;
    }

    public Long getContactNo() {
        return contactNo;
    }

    public void setContactNo(Long contactNo) {
        this.contactNo = contactNo;
    }

}
