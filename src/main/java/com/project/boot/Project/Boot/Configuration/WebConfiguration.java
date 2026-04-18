package com.project.boot.Project.Boot.Configuration;

import com.project.boot.Project.Boot.Accounts.Service.BusinessDetailService;
import com.project.boot.Project.Boot.Accounts.Service.CustomerDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebConfiguration {

    private final CustomerDetailService customerDetailService;
    private final BusinessDetailService businessDetailService;

    public WebConfiguration(CustomerDetailService customerDetailService,
                            BusinessDetailService businessDetailService) {
        this.customerDetailService = customerDetailService;
        this.businessDetailService = businessDetailService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/customer-admin/create-account").permitAll()
                        .requestMatchers(HttpMethod.POST, "/customer-user/create-account").permitAll()
                        .requestMatchers("/customer-user/**").authenticated()
                        .requestMatchers("/customer-admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/business-admin/create-account").permitAll()
                        .requestMatchers(HttpMethod.POST, "/business-user/create-account").permitAll()
                        .requestMatchers("/business-user/**").authenticated()
                        .requestMatchers("/business-admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() {

        DaoAuthenticationProvider customerProvider = new DaoAuthenticationProvider();
        customerProvider.setUserDetailsService(customerDetailService);
        customerProvider.setPasswordEncoder(passwordEncoder());

        DaoAuthenticationProvider businessProvider = new DaoAuthenticationProvider();
        businessProvider.setUserDetailsService(businessDetailService);
        businessProvider.setPasswordEncoder(passwordEncoder());

        return new ProviderManager(customerProvider, businessProvider);
    }

}
