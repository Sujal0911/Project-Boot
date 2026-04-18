package com.project.boot.Project.Boot.Accounts.Controller;

import com.project.boot.Project.Boot.Accounts.DTO.BusinessDTO;
import com.project.boot.Project.Boot.Accounts.Entity.Business;
import com.project.boot.Project.Boot.Accounts.Service.BusinessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("business-admin")
public class BusinessAdminController {

    private final BusinessService businessService;

    public BusinessAdminController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @GetMapping("list")
    public List<Business> allAccounts(){
        return businessService.allAccounts();
    }

    @PostMapping("create-account")
    public ResponseEntity<?> createAdminAccount(@RequestBody Business business) {
        Business business1 = businessService.createAdminAccount(business);
        if (business1 != null) {
            return new ResponseEntity<>(business, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PatchMapping("update")
    public ResponseEntity<?> updateCustomer(@RequestBody BusinessDTO request){

        Business updateBusiness = businessService.updateBusiness(request);

        if(updateBusiness != null){
            return new ResponseEntity<>(updateBusiness, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
