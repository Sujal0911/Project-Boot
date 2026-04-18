package com.project.boot.Project.Boot.Accounts.Service;

import com.project.boot.Project.Boot.Accounts.Entity.Business;
import com.project.boot.Project.Boot.Accounts.Repository.BusinessRepository;
import com.project.boot.Project.Boot.SecurityModel.BusinessUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BusinessDetailService implements UserDetailsService {

    private final BusinessRepository businessRepository;

    public BusinessDetailService(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Business user = businessRepository.findBusinessByUserID(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new BusinessUserDetails(user);
    }
}
