package com.project.boot.Project.Boot.Accounts.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.stereotype.Service;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Business extends Account {

// Fields
    @Column(nullable = false)
    private String pan;
    private String gst;

}
