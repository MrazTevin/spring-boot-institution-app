package com.ke.institutions.service;

import com.ke.institutions.Dto.InstitutionDto;
import com.ke.institutions.Exceptions.DuplicateInstitutionException;
import com.ke.institutions.entity.Institution;
import com.ke.institutions.respository.InstitutionRepository;
import com.ke.institutions.restApi.InstitutionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstitutionService {
    private final InstitutionRepository institutionRepository;
    @Autowired
    public InstitutionService(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    // CRUD DB operations

    public Institution createInstitution(Institution institution) throws DuplicateInstitutionException {
        if (institutionRepository.findByName(institution.getName()).isPresent()) {
            throw new DuplicateInstitutionException("An institution with the same name already exists");
        }
        return institutionRepository.save(institution);
    }

    public List<Institution> searchInstitutionsByName(String institutionName) {
        return institutionRepository.findByNameContainingIgnoreCase(institutionName);
    }


    public Institution getInstitution(Long id) {
        return institutionRepository.findById(id).orElse(null);
    }

    public List<Institution> getAllInstitutions() {
        return institutionRepository.findAll();
    }

    public Institution updateInstitution(Institution institution) {
        return institutionRepository.save(institution);
    }

    public void deleteInstitution(Long id) {
        institutionRepository.deleteById(id);
    }

    public List<Institution> searchInstitutionsById(Long id) {
        return institutionRepository.findById(getInstitution(id));
    }

    public List<Institution> getAllInstitutionsSortedByName() {
        // Fetch all institutions from the repository and sort them by name in ascending order
        return institutionRepository.findAll()
                .stream()
                .sorted((i1, i2) -> i1.getName().compareToIgnoreCase(i2.getName()))
                .collect(Collectors.toList());
    }

}
