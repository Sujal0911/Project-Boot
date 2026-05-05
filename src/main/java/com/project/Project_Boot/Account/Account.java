package com.project.Project_Boot.Account;

import com.project.Project_Boot.Enums.AccountRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    private String userId;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private AccountRole role;
    private Integer age;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private Long contactNo;

}
