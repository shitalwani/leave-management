package com.lm.leavemanagement.repository;

import com.lm.leavemanagement.entity.EmployeesSecurityPIN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesSecurityPINRepository extends JpaRepository<EmployeesSecurityPIN, Integer> {
}
