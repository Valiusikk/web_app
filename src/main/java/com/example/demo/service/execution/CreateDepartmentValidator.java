package com.example.demo.service.execution;

import com.example.demo.exception.EntityAlreadyExistsException;
import com.example.demo.repository.DepartmentRepository;

public class CreateDepartmentValidator extends AbstractDepartmentValidator {

    public CreateDepartmentValidator(DepartmentRepository repository) {
        super(repository);
    }


    public void validateExistence(String name) {
        if (repository.findByDepartmentName(name).isPresent()) {
            throw new EntityAlreadyExistsException(CLASS_NAME, SPECIFIED_NAME, name);
        }
    }
}
