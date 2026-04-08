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
        Business business = businessRepository.findBusinessByUserID(business1.getUserID());
        if(business != null){
            business.setFirstName(business1.getFirstName());
            businessRepository.save(business);
            return business;
}
        else{
            return null;
        }
    }

    public Business changeLastName(Business business1){
        Business business = businessRepository.findBusinessByUserID(business1.getUserID());
        if(business != null){
            business.setLastName(business1.getFirstName());
            businessRepository.save(business);
            return business;
        }
        else{
            return null;
        }
    }

    public Business changeEmail(Business business1){
        Business business = businessRepository.findBusinessByUserID(business1.getUserID());
        if(business != null){
            business.setEmail(business1.getFirstName());
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
            business.setAddress(business1.getFirstName());
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
        Business business = businessRepository.findBusinessByUserID(business1.getUserID());
        if(business != null){
            business.setPAN(business1.getPAN());
            businessRepository.save(business);
            return business;
        }
        else{
            return null;
        }
    }

    public Business changeGst(Business business1){
        Business business = businessRepository.findBusinessByUserID(business1.getUserID());
        if(business != null){
            business.setGST(business1.getPAN());
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
