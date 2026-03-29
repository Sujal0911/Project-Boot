package com.project.boot.Project.Boot.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Business extends Account{

    @Column(nullable = false)
    String PAN;
    String GST;
//    Long businessId;

    public Business(){

    }

    public Business(String firstName, String lastName, String email, String username, String password, String address, Integer age, Long contactNo, String PAN, String GST) {
        super(firstName, lastName, email, username, password, address, age, contactNo);
        this.PAN = PAN;
        this.GST = GST;
    }

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

//    public Long getBusinessId() {
//        return businessId;
//    }
//
//    public void setBusinessId(Long businessId) {
//        this.businessId = businessId;
//    }
}
