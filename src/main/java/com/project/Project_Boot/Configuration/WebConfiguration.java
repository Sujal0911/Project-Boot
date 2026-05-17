package com.project.Project_Boot.Configuration;

import com.project.Project_Boot.Business.Security.BusinessDetailService;
import com.project.Project_Boot.Customer.Security.CustomerDetailService;
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
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/customer-admin/create-account").permitAll()
                        .requestMatchers(HttpMethod.POST, "/customer-user/create-account").permitAll()
                        .requestMatchers("/customer-user/**").authenticated()
                        .requestMatchers("/customer-admin/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/business-admin/create-account").permitAll()
                        .requestMatchers(HttpMethod.POST, "/business-user/create-account").permitAll()
                        .requestMatchers("/business-user/**").authenticated()
                        .requestMatchers("/business-admin/**").hasRole("ADMIN")

                        .requestMatchers("/booking/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Configuration
    public class CorsConfig implements WebMvcConfigurer {

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("http://localhost:5173")
                    .allowedMethods("*");
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
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