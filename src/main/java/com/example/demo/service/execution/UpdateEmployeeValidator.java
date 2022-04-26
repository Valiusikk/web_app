package com.example.demo.service.execution;

import com.example.demo.exception.EntityAlreadyExistsException;
import com.example.demo.repository.EmployeeRepository;

public class UpdateEmployeeValidator extends AbstractEmployeeValidator{
    public UpdateEmployeeValidator(EmployeeRepository repository) {
        super(repository);
    }

    @Override
    public void validateExistence(String email) {
        if(repository.findByEmail(email).isEmpty()){
            throw new EntityAlreadyExistsException(CLASS_NAME,SPECIFIED_NAME,email);
        }
    }
}
