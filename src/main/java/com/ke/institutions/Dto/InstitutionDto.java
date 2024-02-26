package com.ke.institutions.Dto;

import com.ke.institutions.entity.Institution;
import lombok.Data;

@Data
public class InstitutionDto {

    private String status;
    private int statusCode;
    private String message;
    private Institution institution;
}
