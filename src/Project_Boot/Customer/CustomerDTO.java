package com.project.Project_Boot.Customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {

    private String password;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private String address;
    private Long contactNo;
}
