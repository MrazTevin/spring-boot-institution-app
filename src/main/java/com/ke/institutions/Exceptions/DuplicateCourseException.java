package com.ke.institutions.Exceptions;

public class DuplicateCourseException extends RuntimeException{
    public DuplicateCourseException(String message) {
        super(message);
    }

}
