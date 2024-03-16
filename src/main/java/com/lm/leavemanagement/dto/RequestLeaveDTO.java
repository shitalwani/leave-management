package com.lm.leavemanagement.dto;

import com.lm.leavemanagement.entity.EmployeesEntity;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class RequestLeaveDTO {

    private String employeeId;

    private Date leaveDate;

    private Integer numberOfDays;

    private String leaveType;
}
