package com.lm.leavemanagement.dto;

import com.lm.leavemanagement.entity.EmployeesSecurityPIN;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.Date;

@Data
public class RequestEmployeeDTO {
    private String firstName;

    private String lastName;

    private String emailId;

    private String phoneNo;

    private Date joinedOn;

    @Enumerated(EnumType.STRING)
    private EmployeeStatusEnum.EMPLOYEE_BAND employeeBand;

    @Enumerated(EnumType.STRING)
    private EmployeeStatusEnum.LOCATION_STATUS locationStatus;

    private String role;

    private Integer employeesSecurityPIN;

}
