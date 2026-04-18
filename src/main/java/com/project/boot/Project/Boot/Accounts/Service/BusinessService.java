package com.project.boot.Project.Boot.Accounts.Service;

import com.project.boot.Project.Boot.Accounts.DTO.BusinessDTO;
import com.project.boot.Project.Boot.Accounts.Entity.Business;
import com.project.boot.Project.Boot.BusinessFeatures.Entity.Services;
import com.project.boot.Project.Boot.Accounts.Repository.BusinessRepository;
import com.project.boot.Project.Boot.BusinessFeatures.Repository.ServicesRepository;
import com.project.boot.Project.Boot.Enums.AccountRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessService {

    // Constructors

    private final BusinessRepository businessRepository;
    private final ServicesRepository servicesRepository;
    private final PasswordEncoder passwordEncoder;


    public BusinessService(BusinessRepository businessRepository, ServicesRepository servicesRepository, PasswordEncoder passwordEncoder) {
        this.businessRepository = businessRepository;
        this.servicesRepository = servicesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Methods

    public Business createUserAccount(Business business){
        if(businessRepository.existsBusinessByUserID(business.getUserID())){
            return null;
        }
        business.setPassword(passwordEncoder.encode(business.getPassword()));
        business.setRole(AccountRole.USER);
        businessRepository.save(business);
        return business;
    }

    public Business createAdminAccount(Business business){
        if(businessRepository.existsBusinessByUserID(business.getUserID())){
            return null;
        }
        business.setPassword(passwordEncoder.encode(business.getPassword()));
        business.setRole(AccountRole.ADMIN);
        businessRepository.save(business);
        return business;
    }


    public Business updateBusiness(BusinessDTO request){
        if(request.getUserID() == null)
            return null;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Business business = businessRepository.findBusinessByUserID(auth.getName());

        if(business == null){
            return null;
        }

        if(request.getFirstName() != null && !request.getFirstName().trim().isEmpty()){
            business.setFirstName(request.getFirstName());
        }

        if(request.getLastName() != null && !request.getLastName().trim().isEmpty()){
            business.setLastName(request.getLastName().trim());
        }

        if(request.getPassword() != null && !request.getPassword().trim().isEmpty()){
            business.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        if(request.getEmail() != null && !request.getEmail().trim().isEmpty()){
            business.setEmail(request.getEmail().trim());
        }

        if(request.getAddress() != null && !request.getAddress().trim().isEmpty()){
            business.setAddress(request.getAddress().trim());
        }

        if(request.getContactNo() != null){
            business.setContactNo(request.getContactNo());
        }

        if(request.getAge() != null && request.getAge() >= 18){
            business.setAge(request.getAge());
        }

        if(request.getPan() != null && !request.getPan().trim().isEmpty()){
            business.setPan(request.getPan());
        }

        if(request.getGst() != null && !request.getGst().trim().isEmpty()){
            business.setGst(request.getGst());
        }

        return businessRepository.save(business);
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
