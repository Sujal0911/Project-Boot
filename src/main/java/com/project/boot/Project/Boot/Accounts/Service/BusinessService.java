package com.project.boot.Project.Boot.Accounts.Service;

import com.project.boot.Project.Boot.Accounts.Entity.Business;
import com.project.boot.Project.Boot.Accounts.Entity.Customer;
import com.project.boot.Project.Boot.BusinessFeatures.Entity.Services;
import com.project.boot.Project.Boot.Accounts.Repository.BusinessRepository;
import com.project.boot.Project.Boot.BusinessFeatures.Repository.ServicesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessService {

    // Constructors

    private final BusinessRepository businessRepository;
    private final ServicesRepository servicesRepository;


    public BusinessService(BusinessRepository businessRepository, ServicesRepository servicesRepository) {
        this.businessRepository = businessRepository;
        this.servicesRepository = servicesRepository;
    }

    // Methods

    public boolean createAccount(Business business){
        if(businessRepository.existsBusinessByUserID(business.getUserID())){
            return false;
        }
        businessRepository.save(business);
        return true;
    }

    public Business changeFirstName(Business business1){
        if(business1.getFirstName() == null || business1.getFirstName().trim() == ""){
            return null;
        }
        Business business = businessRepository.findBusinessByUserID(business1.getUserID());
        if(business != null){
            business.setFirstName(business1.getFirstName().trim());
            businessRepository.save(business);
            return business;
}
        else{
            return null;
        }
    }

    public Business changeLastName(Business business1){
        if(business1.getLastName() == null || business1.getLastName().trim() == ""){
            return null;
        }
        Business business = businessRepository.findBusinessByUserID(business1.getUserID());
        if(business != null){
            business.setLastName(business1.getLastName().trim());
            businessRepository.save(business);
            return business;
        }
        else{
            return null;
        }
    }

    public Business changeEmail(Business business1){
        if(business1.getEmail() == null || business1.getEmail().trim() == "" || business1.getEmail().substring(business1.getEmail().length()-10) != "@gmail.com"){
            return null;
        }
        Business business = businessRepository.findBusinessByUserID(business1.getUserID());
        if(business != null){
            business.setEmail(business1.getEmail().trim());
            businessRepository.save(business);
            return business;
        }
        else{
            return null;
        }
    }

    public Business changeAddress(Business business1){
        Business business = businessRepository.findBusinessByUserID(business1.getUserID());
        if(business != null){
            business.setAddress(business1.getAddress().trim());
            businessRepository.save(business);
            return business;
        }
        else{
            return null;
        }
    }

    public Business changeContact(Business business1) {
        Business business = businessRepository.findBusinessByUserID(business1.getUserID());
        if (business != null) {
            business.setContactNo(business1.getContactNo());
            businessRepository.save(business);
            return business;
        } else {
            return null;
        }
    }

    public Business changePan(Business business1){
        if(business1.getPan() == null || business1.getPan().trim() == ""){
            return null;
        }
        Business business = businessRepository.findBusinessByUserID(business1.getUserID());
        if(business != null){
            business.setPan(business1.getPan().trim());
            businessRepository.save(business);
            return business;
        }
        else{
            return null;
        }
    }

    public Business changeGst(Business business1){
        if(business1.getGst() == null || business1.getGst().trim() == ""){
            return null;
        }
        Business business = businessRepository.findBusinessByUserID(business1.getUserID());
        if(business != null){
            business.setGst(business1.getGst().trim());
            businessRepository.save(business);
            return business;
        }
        else{
            return null;
        }
    }

    public String addService(String username, Services service){
        Business business = businessRepository.findBusinessByUserID(username);
        if(business == null){
            return "Business not Found";
        }
        servicesRepository.save(service);
        return "Service added successfully";
    }

    public List<Business> allAccounts(){
        return businessRepository.findAll();
    }

}
