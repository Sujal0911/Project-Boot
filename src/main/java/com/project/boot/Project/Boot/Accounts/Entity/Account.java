package com.project.boot.Project.Boot.Accounts.Entity;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String userID;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String firstName;
    private String lastName;

    private Integer age;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private Long contactNo;

    public Account() {

    }

    public Account(String userID) {
        this.userID = userID;
    }

    public Account(String userID, String firstName) {
        this.userID = userID;
        this.firstName = firstName;
    }

    public Account(String userID, Long contactNo) {
        this.userID = userID;
        this.contactNo = contactNo;
    }

    public Account(String userID, Integer age) {
        this.userID = userID;
        this.age = age;
    }

    public Account(String userID, String password, String firstName, String lastName, String email, String address, Long contactNo) {
        this.userID = userID;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.contactNo = contactNo;
    }

    public Account(String userID, String password, String firstName, String lastName, Integer age, String email, String address, Long contactNo) {
        this.userID = userID;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.address = address;
        this.contactNo = contactNo;
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

    public String getUserID() {
        return userID;
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
