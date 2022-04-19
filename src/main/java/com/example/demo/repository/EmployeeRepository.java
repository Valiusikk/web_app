package com.example.demo.repository;


import com.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    /**
     * Should return optional of employee I look for in repository
     * If there is no employee with such email, return empty optional
     *
     * @param email email of employee that I look for
     * @return Optional of found employee or empty optional
     */
    Optional<Employee> findByEmail(String email);
}
