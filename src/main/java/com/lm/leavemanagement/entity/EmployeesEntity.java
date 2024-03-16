package com.lm.leavemanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "employees")
public class EmployeesEntity {

    @Id
    private String employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "phone_no")
    private String phoneNo;

    @Column(name = "joined_on")
    private Date joinedOn;

    @Column(name = "employee_band")
    private String employeeBand;

    @Column(name = "location")
    private String location;

    @Column(name = "role")
    private String role;

    @OneToOne(mappedBy = "employeesEntity", cascade=CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private EmployeesSecurityPIN employeesSecurityPIN;
}
