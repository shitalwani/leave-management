package com.lm.leavemanagement.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.Date;
@Data
public class ResponseEmployeeDTO {

    private String firstName;

    private String lastName;

    private String emailId;

    private String phoneNo;

    private Date joinedOn;

    //@Enumerated(EnumType.STRING)
    private String employeeBand;

    //@Enumerated(EnumType.STRING)
    private String locationStatus;

    private String role;

    private Integer employeesSecurityPIN;
}
