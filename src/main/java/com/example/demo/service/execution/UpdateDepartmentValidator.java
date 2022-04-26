package com.example.demo.service.execution;

import com.example.demo.exception.EntityAlreadyExistsException;
import com.example.demo.repository.DepartmentRepository;

public class UpdateDepartmentValidator extends AbstractDepartmentValidator{
    public UpdateDepartmentValidator(DepartmentRepository repository) {
        super(repository);
    }

    @Override
    public void validateExistence(String name) {
        if(repository.findByDepartmentName(name).isEmpty()){
            throw new EntityAlreadyExistsException(CLASS_NAME, SPECIFIED_NAME,name);
        }
    }
}
