package com.example.demo.service.execution;

import com.example.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractEmployeeValidator extends AbstractValidator{

    protected static final String CLASS_NAME = "Employee";

    protected static final String SPECIFIED_NAME = "Email";

    protected final EmployeeRepository repository;

    public abstract void validateExistence(String key);
}
