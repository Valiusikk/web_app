package com.example.demo.exception;

import com.example.demo.dto.Details;

public class EntityNotFoundException extends WebAppException{
    private static final String MESSAGE = "%s with %s %s doesn't exist";

    private final String objectValue;

    private final String specifiedName;

    @Override
    public String getTitle() {
        return "Not found entity";
    }

    public EntityNotFoundException(String objectName, String specifiedName, String objectValue) {
        super(objectName);
        this.objectValue = objectValue;
        this.specifiedName = specifiedName;
    }

    @Override
    public Details getDetails() {
        return new Details(
                objectName,
                String.format(MESSAGE, objectName, specifiedName, objectValue)
        );
    }
}
