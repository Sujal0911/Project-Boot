package com.project.boot.Project.Boot.Services;

import com.project.boot.Project.Boot.Entity.Business;
import com.project.boot.Project.Boot.Entity.Customer;
import com.project.boot.Project.Boot.Repository.BusinessRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessService {

    private final BusinessRepository businessRepository;

    public BusinessService(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    public String createAccount(Business business){
        if(businessRepository.existsBusinessByUsername(business.getUsername())){
            return "Username is taken";
        }
        businessRepository.save(business);
        return "Success";
    }

    public List<Business> allAccounts(){
        return businessRepository.findAll();
    }
}