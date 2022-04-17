package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PUBLIC)//is possible to set visibility level
@ToString
@Builder

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private String id;

    @NotBlank
    @NotNull
    @NotEmpty
    @Column(name = "first_name")
    private String name;

    @NotBlank
    @NotNull
    @NotEmpty
    @Column(name = "last_name")
    private String surname;

    @NotBlank
    @NotNull
    @NotEmpty
    @Column(name = "email")
    private String email;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department departmentId;

    @NotBlank
    @NotNull
    @NotEmpty
    @Column(name = "phone_number")
    private String phoneNumber;

    @Min(1L)
    @Column(name = "salary")
    private float salary;

    public Employee(){
        id = UUID.randomUUID().toString();
    }

    public Employee(String id, String name, String surname, String email, @NonNull Department departmentId, String phoneNumber, float salary) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.departmentId = departmentId;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
    }
}
