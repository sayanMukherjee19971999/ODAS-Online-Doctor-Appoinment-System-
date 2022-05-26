package com.odas.odas.exception;

public class ResourceNotFoundException extends RuntimeException {
    String domainName;
    String fieldName;
    int fieldValue;

    public ResourceNotFoundException(String domainName, String fieldName, int fieldValue) {
        super(domainName + " with field " + fieldName + " and value " + fieldValue + "not found in the database");
        this.domainName = domainName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

}
