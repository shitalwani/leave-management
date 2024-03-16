package com.lm.leavemanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "employeesSecurityPIN")
public class EmployeesSecurityPIN {

    @Id
    @Column(name = "security_pin_id")
    private String securityPinId;

    @Column(name = "pin")
    private Integer pin;

    @ToString.Exclude
    @JsonIgnore
    @OneToOne
    @MapsId
    @JoinColumn(name = "employeeId")
    private EmployeesEntity employeesEntity;
}
