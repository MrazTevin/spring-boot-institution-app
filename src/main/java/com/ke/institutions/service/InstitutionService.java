package com.ke.institutions.service;

import com.ke.institutions.Exceptions.DuplicateInstitutionException;
import com.ke.institutions.entity.Institution;
import com.ke.institutions.respository.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
