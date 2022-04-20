package com.example.demo.service.execution;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.exception.EntityAlreadyExistsException;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Component("EmployeeValidator")
public class EmployeeValidator{

    EmployeeRepository repository;

    public void validateExistenceOfEmployee(String email) {
        final Optional<Employee> newEmployee = repository.findByEmail(email);
        if (newEmployee.isPresent()) {
            throw new EntityAlreadyExistsException("Employee","email",email);
        }
    }

    public void validateAbsenceOfEmployee(String email){
        final Optional<Employee> newEmployee = repository.findByEmail(email);
        if (newEmployee.isEmpty()) {
            throw new EntityNotFoundException("Employee","email",email);
        }
    }
}
