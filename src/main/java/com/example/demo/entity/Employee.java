package com.example.demo.entity;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

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

@Entity
@Getter
@Setter()
@ToString
@AllArgsConstructor
@Table(name = "employee")
public class Employee {
    @Id
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

    public Employee() {
        id = UUID.randomUUID().toString();
    }

}
