package com.ke.institutions.respository;

import com.ke.institutions.entity.Institution;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


import java.util.Optional;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {
    Optional<Institution> findByName(String name);



    List<Institution> findByNameContainingIgnoreCase(String name);

    @NotNull
    Optional<Institution> findById(@NotNull Long id);

    boolean existsByNameAndIdNot(String newName, Long institutionId);
}
