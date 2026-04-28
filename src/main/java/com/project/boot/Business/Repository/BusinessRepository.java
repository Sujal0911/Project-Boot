package com.project.boot.Business.Repository;

import com.project.boot.Business.Business;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<Business, Long> {
    boolean existsBusinessByUserId(String userID);
    Business findBusinessByUserId(String userID);

}