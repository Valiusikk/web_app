package com.example.demo.exception;

import com.example.demo.dto.Details;
import lombok.Getter;

@Getter
public class WebAppException extends RuntimeException{

    protected final String objectName;

    public Details getDetails(){
        return new Details(objectName, "Exception");
    }

    public String getTitle(){
        return "TITLE";
    }
    public WebAppException() {
        super("");
        this.objectName = "";
    }

    public WebAppException(final String objectName) {
        super("");
        this.objectName = objectName;
    }
}
