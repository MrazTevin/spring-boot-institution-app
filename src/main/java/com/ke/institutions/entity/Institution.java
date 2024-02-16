package com.ke.institutions.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String name;
    @OneToMany(mappedBy = "institution", cascade = CascadeType.ALL)
    private Set<Course> courses;
}

