package com.lm.leavemanagement.repository;

import com.lm.leavemanagement.entity.EmployeesEntity;
import com.lm.leavemanagement.entity.LeaveRegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRegisterRepository extends JpaRepository<LeaveRegisterEntity,Integer> {
    List<LeaveRegisterEntity> findByEmployeesEntity(EmployeesEntity employeesEntity);
    // LeaveRegisterEntity findByEmployeeId(String employeeId);
}
