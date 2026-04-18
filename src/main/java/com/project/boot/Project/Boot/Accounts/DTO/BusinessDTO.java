package com.project.boot.Project.Boot.Accounts.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessDTO {

    private String userID;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private Long contactNo;
    private Integer age;
    private String pan;
    private String gst;
}