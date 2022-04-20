package com.example.demo.repository;

import com.example.demo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, String> {
    /**
     * Should return optional of department I look for in repository
     * If there is no department with such name, return empty optional
     *
     * @param departmentName name of department that I look for
     * @return Optional of found department or empty optional
     */
    Optional<Department> findByDepartmentName(String departmentName);
}
