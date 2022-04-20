package com.example.demo.entity;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "employee_id")
    private UUID id;

    @NotBlank(message = "Employee must have valid first name")
    @NotNull(message = "Employee's first name must not be null")
    @NotEmpty(message = "Employee's first name must not be empty string")
    @Column(name = "first_name")
    private String name;

    @NotBlank(message = "Employee must have valid surname")
    @NotNull(message = "Employee's surname must not be null")
    @NotEmpty(message = "Employee's surname must not be empty string")
    @Column(name = "last_name")
    private String surname;

    @Column(name = "email")
    @Pattern(regexp = "^(.+)@(.+)$")
    @NotBlank(message = "Employee email should contain valid email")
    @NotNull(message = "Employee email should not be null")
    @NotEmpty(message = "Employee email should not be empty string")
    private String email;

    @NotNull(message = "Employee should have department")
    @ManyToOne(targetEntity = Department.class)
    @JoinColumn(name = "department_id")
    private Department departmentId;

    @NotBlank(message = "Each employee should have own unique phone number")
    @NotNull(message = "Employee's phone number should be not null")
    @NotEmpty(message = "Employee's phone number should be not an empty string")
    @Column(name = "phone_number")
    private String phoneNumber;

    @Min(value = 1L,message = "Minimal salary of employee is 1 monetary unit")
    @Column(name = "salary")
    private float salary;

    public Employee() {
        id = UUID.randomUUID();
    }

}
