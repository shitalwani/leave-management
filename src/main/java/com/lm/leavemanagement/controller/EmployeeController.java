package com.lm.leavemanagement.controller;

import com.lm.leavemanagement.dto.*;
import com.lm.leavemanagement.entity.EmployeesEntity;
import com.lm.leavemanagement.entity.EmployeesSecurityPIN;
import com.lm.leavemanagement.entity.LeaveRegisterEntity;
import com.lm.leavemanagement.service.EmployeeService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<EmployeesEntity> addEmployees(@RequestBody RequestEmployeeDTO requestEmployeeDTO){
        EmployeesEntity addEmployee = employeeService.createEmployee(requestEmployeeDTO);
        return new ResponseEntity<>(addEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/get/{employeeId}")
    public ResponseEntity<ResponseEmployeeDTO> fetchByEmployeeId(@PathVariable(value = "employeeId")String employeeId){
        ResponseEmployeeDTO getData = employeeService.getDataById(employeeId);
        return new ResponseEntity<>(getData,HttpStatus.OK);
    }

    @PostMapping("/addLeave")
    public ResponseEntity<LeaveRegisterEntity> addLeaves(@RequestBody RequestLeaveDTO requestLeaveDTO){
        LeaveRegisterEntity addLeave = employeeService.addEmployeeLeaves(requestLeaveDTO);
        return new ResponseEntity<>(addLeave,HttpStatus.CREATED);
    }

    @PostMapping("/validatePin")
    public ResponseEntity<String> validatePin(@RequestBody PINValidationDTO pinValidationDTO){
        String validatePIN = employeeService.pinValidation(pinValidationDTO);
        return new ResponseEntity<>(validatePIN,HttpStatus.OK);
    }

    @GetMapping("/leave-report")
    public ResponseEntity<List<ResponseLeaveDTO>> getLeaveReport(@RequestParam(value = "employeeId") String employeeId ){
        List<ResponseLeaveDTO> getReport = employeeService.fetchLeaveReport(employeeId);
        return new ResponseEntity<>(getReport,HttpStatus.OK);

    }

}
