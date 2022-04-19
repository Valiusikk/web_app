package com.example.demo.repository;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, String> {
    DepartmentDTO findByDepartmentName(String departmentName);
}
