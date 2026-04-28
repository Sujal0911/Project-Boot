package com.project.boot.Business;

import com.project.boot.Account.Account;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Business extends Account {

    @Column(nullable = false)
    private String pan;
    private String gst;

}
