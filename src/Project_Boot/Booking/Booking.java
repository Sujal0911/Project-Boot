package com.project.Project_Boot.Booking;

import com.project.Project_Boot.BusinessFeatures.Entity.Cart;
import com.project.Project_Boot.BusinessFeatures.Entity.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Entity
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @OneToMany
    @JoinColumn(name = "Purchased_Items")
    private List<Product> products = new ArrayList<>();
    private double total;

}
