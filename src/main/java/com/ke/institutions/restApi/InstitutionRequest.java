package com.ke.institutions.restApi;

import com.ke.institutions.Dto.InstitutionDto;
import com.ke.institutions.entity.Course;
import com.ke.institutions.entity.Institution;
import com.ke.institutions.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("/{institutionId}/courses")
    public ResponseEntity<Institution> createCourse(@PathVariable Long institutionId, @RequestBody Course course) {
        return ResponseEntity.ok(institutionService.createCourse(institutionId, course).getInstitution());
    }

    @GetMapping("/{institutionId}/courses/list")
    public ResponseEntity<List<Course>> getAllCoursesByInstitution(@PathVariable Long institutionId) {
        List<Course> courses = institutionService.getAllCoursesByInstitution(institutionId);
        return ResponseEntity.ok(courses);
    }
    private InstitutionDto mapToInstitutionDto(Institution institution) {
        InstitutionDto institutionDTO = new InstitutionDto();
        institutionDTO.setInstitutionId(institution.getId());
        institutionDTO.setName(institution.getName());
        return institutionDTO;
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

    @GetMapping(value = "/search", params = "name")
    public ResponseEntity<?> searchInstitutionsByName(@RequestParam(required = false) String name) {
        List<Institution> institutions = institutionService.searchInstitutionsByName(name);
        if (institutions.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(institutions);
        }
    }

    @GetMapping(value = "/institution/search", params = "id")
    public ResponseEntity<?> searchInstitutionsById(@RequestParam Long id) {
        Optional<Institution> institution = institutionService.findById(id);
        if (institution.isPresent()) {
            return ResponseEntity.ok(institution.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("list/{id}")
    public ResponseEntity<Institution> getInstitution(@PathVariable Long id) {
        Institution institution = institutionService.getInstitution(id);
        if (institution !=null ) {
            return ResponseEntity.ok(institution);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/institution/{id}")
    public ResponseEntity<?> editInstitutionName(@PathVariable Long id, @RequestBody String newName) {
        return ResponseEntity.ok(institutionService.editInstitutionName(id, newName));
    }

    @PutMapping("/{institutionId}")
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
