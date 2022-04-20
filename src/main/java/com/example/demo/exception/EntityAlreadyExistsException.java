package com.example.demo.exception;

import com.example.demo.dto.Details;

public class EntityAlreadyExistsException extends WebAppException{

    private static final String MESSAGE = "%s with %s %s is already in database";

    private final String objectValue;

    private final String specifiedName;

    @Override
    public String getTitle() {
        return "Duplicate entity";
    }

    public EntityAlreadyExistsException(String objectName, String specifiedName, String objectValue) {
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
