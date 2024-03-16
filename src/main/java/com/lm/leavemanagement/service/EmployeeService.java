package com.lm.leavemanagement.service;

import com.lm.leavemanagement.dto.*;
import com.lm.leavemanagement.entity.EmployeesEntity;
import com.lm.leavemanagement.entity.EmployeesSecurityPIN;
import com.lm.leavemanagement.entity.LeaveRegisterEntity;

import java.util.List;


public interface EmployeeService {

    EmployeesEntity createEmployee(RequestEmployeeDTO requestEmployeeDTO);

    ResponseEmployeeDTO getDataById(String employeeId);

    LeaveRegisterEntity addEmployeeLeaves(RequestLeaveDTO requestLeaveDTO);

    String pinValidation(PINValidationDTO pinValidationDTO);

    List<ResponseLeaveDTO> fetchLeaveReport(String employeeId);
}
