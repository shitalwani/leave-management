package com.lm.leavemanagement.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class ResponseLeaveDTO {

    private Date leaveDate;

    private Integer numberOfDays;

    private String leaveType;

}
