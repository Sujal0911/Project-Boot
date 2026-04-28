package com.project.boot.Business.Service;

import com.project.boot.Business.Business;
import com.project.boot.Business.BusinessDTO;
import com.project.boot.Business.Repository.BusinessRepository;
import com.project.boot.BusinessFeatures.DTO.ProductDTO;
import com.project.boot.BusinessFeatures.Entity.Product;
import com.project.boot.BusinessFeatures.Repository.ProductRepository;
import com.project.boot.Customer.Repository.CustomerRepository;
import com.project.boot.Enums.AccountRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BusinessService {

    // Constructors

    private final BusinessRepository businessRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;


    public BusinessService(BusinessRepository businessRepository, ProductRepository productRepository, PasswordEncoder passwordEncoder, CustomerRepository customerRepository) {
        this.businessRepository = businessRepository;
        this.productRepository = productRepository;
        this.passwordEncoder = passwordEncoder;
        this.customerRepository = customerRepository;
    }

    // Methods

    public Business createUserAccount(Business business){
        if(businessRepository.existsBusinessByUserId(business.getUserId()) || customerRepository.existsCustomerByUserId(business.getUserId())){
            return null;
        }
        business.setPassword(passwordEncoder.encode(business.getPassword()));
        business.setRole(AccountRole.USER);
        businessRepository.save(business);
        return business;
    }

    public Business createAdminAccount(Business business){
        if(businessRepository.existsBusinessByUserId(business.getUserId()) || customerRepository.existsCustomerByUserId(business.getUserId())){
            return null;
        }
        business.setPassword(passwordEncoder.encode(business.getPassword()));
        business.setRole(AccountRole.ADMIN);
        businessRepository.save(business);
        return business;
    }


    public Business updateBusiness(BusinessDTO request){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Business business = businessRepository.findBusinessByUserId(auth.getName());

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

    public Product addService(Product product){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Business business = businessRepository.findBusinessByUserId(auth.getName());
        if(business == null){
            return null;
        }
        product.setBusinessId(business.getUserId());
        productRepository.save(product);
        return product;
    }

    @Transactional
    public void deleteService(Long Id) {
        if (Id == null) {
            throw new RuntimeException("Service ID cannot be null");
        }

        productRepository.deleteProductById(Id);
    }

    public Product updateService(ProductDTO product) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!product.getBusinessId().equals(auth.getName())){
             throw new RuntimeException("Wrong UserID");
        }

        Product serviceInfo = productRepository.findProductById(product.getId());
        if(serviceInfo == null) {
            return null;
        }

        serviceInfo.setPrice(product.getPrice());
        serviceInfo.setProductName(product.getProductName());
        serviceInfo.setProductType(product.getProductType());
        serviceInfo.setAvailable(product.isAvailable());
        serviceInfo.setDuration(product.getDuration());
        serviceInfo.setDescription(product.getDescription());

        productRepository.save(serviceInfo);

        return serviceInfo;
    }

    public List<Product> allServicesOfBusiness(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Product> allServices = productRepository.findProductsByBusinessId(auth.getName());
        return allServices;
    }

    public List<Business> allAccounts(){
        return businessRepository.findAll();
    }

}
