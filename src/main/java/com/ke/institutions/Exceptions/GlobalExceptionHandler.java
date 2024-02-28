package com.ke.institutions.Exceptions;

import com.ke.institutions.Dto.InstitutionError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateInstitutionException.class)
    public ResponseEntity<InstitutionError> handleInstitutionAlreadyExists(DuplicateInstitutionException ex) {
        InstitutionError response = new InstitutionError();
        response.setStatus("error");
        response.setStatusCode(400);
        response.setMessage(ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
