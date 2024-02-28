package com.ke.institutions.Dto;

import lombok.Data;

@Data
public class CreateInstitutionResponseDTO {

    private String status;
    private int statusCode;
    private String message;
    private InstitutionDto institution;
}
