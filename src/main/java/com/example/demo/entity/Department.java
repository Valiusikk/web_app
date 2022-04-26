package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "department")
public class Department {
    @Id
    @Column(name = "department_id")
    private String departmentId;

    @Column(name = "department_name")
    @NotNull(message = "Each department must have valid name")
    private String departmentName;

    @Column(name = "location_name")
    private String location;

    @OneToMany(mappedBy = "departmentId")
    private Set<Employee> employees;
}
