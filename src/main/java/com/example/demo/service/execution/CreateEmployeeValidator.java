package com.example.demo.service.execution;

import com.example.demo.exception.EntityAlreadyExistsException;
import com.example.demo.repository.EmployeeRepository;

public class CreateEmployeeValidator extends AbstractEmployeeValidator{

    public CreateEmployeeValidator(EmployeeRepository repository) {
        super(repository);
    }

    @Override
    public void validateExistence(String email) {
        if(repository.findByEmail(email).isPresent()){
            throw new EntityAlreadyExistsException(CLASS_NAME,SPECIFIED_NAME,email);
        }
    }
}
