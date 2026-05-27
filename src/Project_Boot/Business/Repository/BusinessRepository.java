package com.project.Project_Boot.Business.Repository;

import com.project.Project_Boot.Business.Business;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<Business, Long> {
    boolean existsBusinessByUserId(String userID);
    Business findBusinessByUserId(String userID);

}