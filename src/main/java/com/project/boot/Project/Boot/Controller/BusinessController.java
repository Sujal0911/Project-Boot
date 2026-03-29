package com.project.boot.Project.Boot.Controller;

import com.project.boot.Project.Boot.Entity.Business;
import com.project.boot.Project.Boot.Services.BusinessService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Business")
public class BusinessController {

    private final BusinessService businessService;

    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @PostMapping("create")
    public String createAccount(@RequestBody Business business){
        return businessService.createAccount(business);
    }

    @GetMapping("list")
    public List<Business> allAccounts(){
        return businessService.allAccounts();
    }
}
