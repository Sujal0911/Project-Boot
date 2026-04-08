package com.project.boot.Project.Boot.BusinessFeatures.Repository;

import com.project.boot.Project.Boot.BusinessFeatures.Entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Long> {

}
