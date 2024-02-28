package com.ke.institutions.Dto;

import lombok.Data;

@Data
public class InstitutionError {

    private String status;
    private int statusCode;
    private String message;
}
