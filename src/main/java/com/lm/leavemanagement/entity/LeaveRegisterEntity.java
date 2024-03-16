package com.lm.leavemanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "leaveRegisterEntity")
public class LeaveRegisterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(targetEntity = EmployeesEntity.class)
    @JoinColumn(name = "employeeId",referencedColumnName = "employeeId")
    private EmployeesEntity employeesEntity;

    @Column(name = "leave_date")
    private Date leaveDate;

    @Column(name = "number_of_days")
    private Integer numberOfDays;

    @Column(name = "leave_type")
    private String leaveType;
}
