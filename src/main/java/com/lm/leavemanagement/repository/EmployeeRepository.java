package com.lm.leavemanagement.repository;

import com.lm.leavemanagement.entity.EmployeesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeesEntity,String> {
    EmployeesEntity findByEmployeeId(String employeeId);
}
