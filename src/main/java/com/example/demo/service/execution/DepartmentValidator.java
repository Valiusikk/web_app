package com.example.demo.service.execution;
import com.example.demo.entity.Department;
import com.example.demo.exception.EntityAlreadyExistsException;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DepartmentValidator{

    DepartmentRepository repository;

    public void validateExistenceOfDepartment(String name) {
        final Optional<Department> department = repository.findByDepartmentName(name);
        if(department.isPresent()){
            throw new EntityAlreadyExistsException("Department","department name",name);
        }
    }

    public void validateAbsenceOfEmployee(String name){
        final Optional<Department> department = repository.findByDepartmentName(name);
        if (department.isEmpty()) {
            throw new EntityNotFoundException("Department","name",name);
        }
    }
}
