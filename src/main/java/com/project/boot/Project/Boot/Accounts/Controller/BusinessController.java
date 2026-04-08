package com.project.boot.Project.Boot.Accounts.Controller;

import com.project.boot.Project.Boot.Accounts.Entity.Business;
import com.project.boot.Project.Boot.Accounts.Service.BusinessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Business")
public class BusinessController {

    private final BusinessService businessService;

    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @PostMapping("createAccount")
    public ResponseEntity<Business> createAccount(@RequestBody Business business) {
        if (businessService.createAccount(business)) {
            return new ResponseEntity<>(business, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }

    @PutMapping("changeFirstName")
    public ResponseEntity<String> changeFirstName(@RequestBody Business business1) {
        Business business = businessService.changeFirstName(business1);
        if (business != null) {
            return new ResponseEntity<>(business.getFirstName(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("changeLastName")
    public ResponseEntity<String> changeLastName(@RequestBody Business business1) {
        Business business = businessService.changeLastName(business1);
        if (business != null) {
            return new ResponseEntity<>(business.getLastName(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("changeEmail")
    public ResponseEntity<String> changeEmail(@RequestBody Business business1) {
        Business business = businessService.changeEmail(business1);
        if (business != null) {
            return new ResponseEntity<>(business.getEmail(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("changeAddress")
    public ResponseEntity<String> changeAddress(@RequestBody Business business1) {
        Business business = businessService.changeAddress(business1);
        if (business != null) {
            return new ResponseEntity<>(business.getAddress(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("changeContact")
    public ResponseEntity<Long> changeContact(@RequestBody Business business1) {
        Business business = businessService.changeContact(business1);
        if (business != null) {
            return new ResponseEntity<>(business.getContactNo(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("changePan")
    public ResponseEntity<String> changePan(@RequestBody Business business1){
        Business business = businessService.changePan(business1);
        if(business != null){
            return new ResponseEntity<>(business.getPAN(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("changeGst")
    public ResponseEntity<String> changeGst(@RequestBody Business business1){
        Business business = businessService.changeGst(business1);
        if(business != null){
            return new ResponseEntity<>(business.getGST(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("list")
    public List<Business> allAccounts(){
        return businessService.allAccounts();
    }

}