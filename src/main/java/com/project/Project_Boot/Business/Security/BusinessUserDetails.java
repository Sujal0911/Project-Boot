package com.project.Project_Boot.Business.Security;

import com.project.Project_Boot.Business.Business;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
public class BusinessUserDetails implements UserDetails {

    private final Business business;

    public BusinessUserDetails(Business business) {
        this.business = business;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_" + business.getRole().name());
    }

    @Override
    public String getPassword() {
        return business.getPassword();
    }

    @Override
    public String getUsername() {
        return business.getUserId();
    }
}