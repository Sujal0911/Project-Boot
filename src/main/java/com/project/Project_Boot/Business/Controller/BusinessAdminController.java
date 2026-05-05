package com.project.Project_Boot.Business.Controller;

import com.project.Project_Boot.Business.Business;
import com.project.Project_Boot.Business.BusinessDTO;
import com.project.Project_Boot.Business.Service.BusinessService;
import com.project.Project_Boot.BusinessFeatures.DTO.ProductDTO;
import com.project.Project_Boot.BusinessFeatures.Entity.Product;
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

    @PostMapping("add-service")
    public ResponseEntity<?> addService(@RequestBody Product product){
        Product service = businessService.addService(product);
        if(service != null){
            return new ResponseEntity<>(service, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("delete-service/{Id}")
    public ResponseEntity<?> deleteService(@PathVariable Long Id){
        businessService.deleteService(Id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("update-service")
    public ResponseEntity<?> updateService(@RequestBody ProductDTO productDTO){
        Product service = businessService.updateService(productDTO);
        if(service != null){
            return new ResponseEntity<>(service, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("all-services")
    public List<Product> allServices(){
        return businessService.allServicesOfBusiness();
    }
}
