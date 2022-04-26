package com.example.demo.service.execution;


public abstract class AbstractValidator{

    public boolean validate(String key) {
        validateExistence(key);
        return true;
    }

    public abstract void validateExistence(String key);
}
