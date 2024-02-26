package com.ke.institutions.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @ManyToOne
    @JoinColumn(name="institution_id")
    private Institution institution;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<Student> students;
}
