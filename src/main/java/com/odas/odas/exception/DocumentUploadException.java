package com.odas.odas.exception;

public class DocumentUploadException extends RuntimeException {
    String domainName;
    String fieldName;
    String fieldValue;

    public DocumentUploadException(String domainName, String fieldName, String fieldValue) {
        super(domainName + " with field " + fieldName + " and value " + fieldValue
                + "failed to upload in the database");
        this.domainName = domainName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
