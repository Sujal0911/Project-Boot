package com.project.Project_Boot.Customer;

import com.project.Project_Boot.Account.Account;
import com.project.Project_Boot.BusinessFeatures.Entity.Cart;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Customer extends Account {

    @OneToOne
    private Cart cart;
}
