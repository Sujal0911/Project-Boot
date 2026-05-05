package com.project.Project_Boot.Business;

import com.project.Project_Boot.Account.Account;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Business extends Account {

    @Column(nullable = false)
    private String pan;
    private String gst;

}
