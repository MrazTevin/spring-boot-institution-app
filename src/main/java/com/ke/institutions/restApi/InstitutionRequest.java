package com.ke.institutions.restApi;

import com.ke.institutions.Dto.CreateInstitutionResponseDTO;
import com.ke.institutions.Dto.InstitutionDto;
import com.ke.institutions.Dto.InstitutionError;
import com.ke.institutions.Exceptions.DuplicateInstitutionException;
import com.ke.institutions.entity.Institution;
import com.ke.institutions.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/institutions")
public class InstitutionRequest {

    private final InstitutionService institutionService;
    @Autowired
    public InstitutionRequest(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @PostMapping
    public ResponseEntity<Institution> createInstitution(@RequestBody Institution institution) {
        return ResponseEntity.ok(institutionService.createInstitution(institution));
    }
    private InstitutionDto mapToInstitutionDto(Institution institution) {
        InstitutionDto institutionDTO = new InstitutionDto();
        institutionDTO.setInstitutionId(institution.getId());
        institutionDTO.setName(institution.getName());
        return institutionDTO;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Institution> getInstitution(@PathVariable Long id) {
        Institution institution = institutionService.getInstitution(id);
        if (institution !=null ) {
            return ResponseEntity.ok(institution);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/sortedByName")
    public ResponseEntity<List<Institution>> getAllInstitutionsSortedByName() {
        List<Institution> sortedInstitutions = institutionService.getAllInstitutionsSortedByName();
        return ResponseEntity.ok(sortedInstitutions);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Institution>> getAllInstitutions() {
        return ResponseEntity.ok(institutionService.getAllInstitutions());
    }

    @GetMapping("/institution/search")
    public ResponseEntity<?> searchInstitutions(@RequestParam(required = false) Long id, @RequestParam(required = false) String name) {
        if (id != null) {
            // Search by ID
            List<Institution> institutions = institutionService.searchInstitutionsById(id);
            if (institutions.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(institutions);
            }
        } else if (name != null) {
            // Search by name
            List<Institution> institutions = institutionService.searchInstitutionsByName(name);
            if (institutions.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(institutions);
            }
        } else {
            // Handle invalid request
            return ResponseEntity.badRequest().body("Invalid search criteria");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Institution> updateInstitution(@PathVariable Long id, @RequestBody Institution institution) {
        institution.setId(id);
        return ResponseEntity.ok(institutionService.updateInstitution(institution));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstitution(@PathVariable Long id) {
        institutionService.deleteInstitution(id);
        return ResponseEntity.noContent().build();
    }
}
