package com.project.boot.Project.Boot.Accounts.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Business extends Account {

// Fields
    @Column(nullable = false)
    private String PAN;
    private String GST;

// Constructors
    public Business(){

    }

    public Business(String userID, String PAN) {
        super(userID);
        this.PAN = PAN;
    }

    public Business(String userID, String password, String firstName, String lastName, String email, String address, Long contactNo, String PAN, String GST) {
        super(userID, password, firstName, lastName, email, address, contactNo);
        this.PAN = PAN;
        this.GST = GST;
    }

    // Getters & Setters
    public String getPAN() {
        return PAN;
    }

    public void setPAN(String PAN) {
        this.PAN = PAN;
    }

    public String getGST() {
        return GST;
    }

    public void setGST(String GST) {
        this.GST = GST;
    }

}
