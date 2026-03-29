package com.project.boot.Project.Boot.Repository;

import com.project.boot.Project.Boot.Entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<Business, String> {
    boolean existsBusinessByUsername(String username);
}
