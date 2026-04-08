package com.project.boot.Project.Boot.Accounts.Repository;

import com.project.boot.Project.Boot.Accounts.Entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<Business, String> {
    boolean existsBusinessByUserID(String userID);

    Business findBusinessByUserID(String userID);
}