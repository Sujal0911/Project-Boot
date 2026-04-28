package com.project.boot.Customer;

import com.project.boot.Account.Account;
import com.project.boot.BusinessFeatures.Entity.Cart;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;
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
