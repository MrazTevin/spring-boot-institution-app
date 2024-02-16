package com.ke.institutions.restApi;

import com.ke.institutions.entity.Institution;
import com.ke.institutions.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/{id}")
    public ResponseEntity<Institution> getInstitution(@PathVariable Long id) {
        Institution institution = institutionService.getInstitution(id);
        if (institution !=null ) {
            return ResponseEntity.ok(institution);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public ResponseEntity<List<Institution>> getAllInstitutions() {
        return ResponseEntity.ok(institutionService.getAllInstitutions());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Institution> updateInstitution(@PathVariable Long id, @RequestBody Institution institution) {
        institution.setId(id);
        return ResponseEntity.ok(institutionService.updateInstitution(institution));
    }

    public ResponseEntity<Void> deleteInstitution(@PathVariable Long id) {
        institutionService.deleteInstitution(id);
        return ResponseEntity.noContent().build();
    }
}
