package com.project.boot.Business.Security;

import com.project.boot.Business.Business;
import com.project.boot.Business.Repository.BusinessRepository;
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
        Business user = businessRepository.findBusinessByUserId(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new BusinessUserDetails(user);
    }
}
