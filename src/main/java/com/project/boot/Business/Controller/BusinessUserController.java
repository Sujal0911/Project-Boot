package com.project.boot.Business.Controller;

import com.project.boot.Business.Business;
import com.project.boot.Business.BusinessDTO;
import com.project.boot.Business.Service.BusinessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("business-user")
public class BusinessUserController {

    private final BusinessService businessService;

    public BusinessUserController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @PostMapping("create-account")
    public ResponseEntity<?> createAccount(@RequestBody Business business) {
        Business business1 = businessService.createUserAccount(business);
        if (business1 != null) {
            return new ResponseEntity<>(business, HttpStatus.OK);
        }
        return new ResponseEntity<>("Exist", HttpStatus.CONFLICT);
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