package com.ke.institutions.Exceptions;

public class DuplicateInstitutionException extends RuntimeException{
    public DuplicateInstitutionException(String message) {
        super(message);
    }
}
