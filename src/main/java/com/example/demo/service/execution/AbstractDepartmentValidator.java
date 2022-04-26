package com.example.demo.service.execution;

import com.example.demo.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractDepartmentValidator extends AbstractValidator{

    protected static final String CLASS_NAME = "Department";

    protected static final String SPECIFIED_NAME = "Department name";

    protected final DepartmentRepository repository;

    public abstract void validateExistence(String name);
}
