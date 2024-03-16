package com.lm.leavemanagement.implementation;

import com.lm.leavemanagement.dto.*;
import com.lm.leavemanagement.entity.EmployeesEntity;
import com.lm.leavemanagement.entity.EmployeesSecurityPIN;
import com.lm.leavemanagement.entity.LeaveRegisterEntity;
import com.lm.leavemanagement.exception.ApplicationException;
import com.lm.leavemanagement.repository.EmployeeRepository;
import com.lm.leavemanagement.repository.EmployeesSecurityPINRepository;
import com.lm.leavemanagement.repository.LeaveRegisterRepository;
import com.lm.leavemanagement.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImplementation implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeesSecurityPINRepository employeesSecurityPINRepository;

    @Autowired
    LeaveRegisterRepository leaveRegisterRepository;

    @Override
    public EmployeesEntity createEmployee(RequestEmployeeDTO requestEmployeeDTO) {
        String phoneNumber = validatePhone(requestEmployeeDTO.getPhoneNo());

        EmployeesEntity employeesEntity = new EmployeesEntity();
        String employeeId = generateEmployeeId(requestEmployeeDTO.getFirstName(), requestEmployeeDTO.getLastName());
        employeesEntity.setEmployeeId(employeeId);
        employeesEntity.setFirstName(requestEmployeeDTO.getFirstName());
        employeesEntity.setLastName(requestEmployeeDTO.getLastName());
        employeesEntity.setEmailId(requestEmployeeDTO.getEmailId());

        employeesEntity.setEmployeeBand(String.valueOf(requestEmployeeDTO.getEmployeeBand()));
        employeesEntity.setLocation(String.valueOf(requestEmployeeDTO.getLocationStatus()));

        employeesEntity.setRole(requestEmployeeDTO.getRole());
        employeesEntity.setJoinedOn(requestEmployeeDTO.getJoinedOn());
        employeesEntity.setPhoneNo(phoneNumber);

        EmployeesSecurityPIN employeesSecurityPIN = new EmployeesSecurityPIN();
        employeesSecurityPIN.setPin(requestEmployeeDTO.getEmployeesSecurityPIN());
        employeesSecurityPIN.setEmployeesEntity(employeesEntity);
        employeesSecurityPINRepository.save(employeesSecurityPIN);

        return employeeRepository.save(employeesEntity);
    }
    public String generateEmployeeId(String firstName, String lastName){
        String strFirst = String.valueOf(firstName.charAt(0)).toUpperCase();
        String strLast = String.valueOf(lastName.charAt(0)).toUpperCase();
        String str = strFirst.concat(strLast);
        String employeeId = str.concat(String.valueOf((int)(Math.random()*9000)+1000));
        return employeeId;
    }

    public String validatePhone(String phone){
        String phoneNumber = "";
        if(phone.length() < 10 || phone.length() > 10){
            throw new ApplicationException("Phone Number should be valid 10 digit", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST);
        }else if (phone.length() == 10){
            phoneNumber = phone;
        }
        return phoneNumber;
    }
    //----------------------------------------------------------------------------------------------

    @Override
    public ResponseEmployeeDTO getDataById(String employeeId) {
        ResponseEmployeeDTO responseEmployeeDTO = new ResponseEmployeeDTO();
        try {
            EmployeesEntity employeesEntityOptional = employeeRepository.findByEmployeeId(employeeId);
            if (employeesEntityOptional == null) {
                throw new ApplicationException("Record not found for id : " + employeeId,HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST);
            }
            log.info("EmployeesEntity : {}", employeesEntityOptional);
            responseEmployeeDTO.setFirstName(employeesEntityOptional.getFirstName());
            responseEmployeeDTO.setLastName(employeesEntityOptional.getLastName());
            responseEmployeeDTO.setEmployeeBand(employeesEntityOptional.getEmployeeBand());
            responseEmployeeDTO.setEmailId(employeesEntityOptional.getEmailId());
            responseEmployeeDTO.setJoinedOn(employeesEntityOptional.getJoinedOn());
            responseEmployeeDTO.setLocationStatus(employeesEntityOptional.getLocation());
            responseEmployeeDTO.setRole(employeesEntityOptional.getRole());
            responseEmployeeDTO.setEmployeesSecurityPIN(employeesEntityOptional.getEmployeesSecurityPIN().getPin());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseEmployeeDTO;
    }

    @Override
    public LeaveRegisterEntity addEmployeeLeaves(RequestLeaveDTO requestLeaveDTO) {
        LeaveRegisterEntity leaveRegisterEntity = new LeaveRegisterEntity();
        try {
            EmployeesEntity employeesEntity = employeeRepository.findByEmployeeId(requestLeaveDTO.getEmployeeId());
            if (employeesEntity == null) {
                throw new ApplicationException("Invalid employee code", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST);
            }
            leaveRegisterEntity.setLeaveDate(requestLeaveDTO.getLeaveDate());
            leaveRegisterEntity.setLeaveType(requestLeaveDTO.getLeaveType());
            leaveRegisterEntity.setNumberOfDays(requestLeaveDTO.getNumberOfDays());
            leaveRegisterEntity.setEmployeesEntity(employeesEntity);
        }catch (Exception e){
            throw new ApplicationException(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return leaveRegisterRepository.save(leaveRegisterEntity);
    }

    @Override
    public String pinValidation(PINValidationDTO pinValidationDTO) {
        String responseString = "";
        EmployeesEntity employeesEntity = employeeRepository.findByEmployeeId(pinValidationDTO.getEmployeeId());
        if(employeesEntity == null){
            throw new ApplicationException("Invalid Employee Id", HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST);
        }
        if (employeesEntity.getEmployeesSecurityPIN().getPin().equals(pinValidationDTO.getPin())) {
            responseString = "valid pin";

        } else {
            throw new ApplicationException("Invalid pin", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST);
        }
        return responseString;

    }

    @Override
    public List<ResponseLeaveDTO> fetchLeaveReport(String employeeId) {
         EmployeesEntity employeesEntity = employeeRepository.findByEmployeeId(employeeId);

         List<LeaveRegisterEntity> leaveRegisterEntity = leaveRegisterRepository.findByEmployeesEntity(employeesEntity);

        List<ResponseLeaveDTO> responseLeaveDTOList = new ArrayList<>();

        leaveRegisterEntity.forEach(data ->{
                ResponseLeaveDTO responseLeaveDTO = new ResponseLeaveDTO();
                responseLeaveDTO.setLeaveDate(data.getLeaveDate());
                responseLeaveDTO.setLeaveType(data.getLeaveType());
                responseLeaveDTO.setNumberOfDays(data.getNumberOfDays());
            responseLeaveDTOList.add(responseLeaveDTO);
                    });

            return responseLeaveDTOList;
    }


}
