package com.ke.institutions.entity;

import jakarta.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @ManyToOnegit
    @JoinColumn(name = "course_id")
    private Course course;
}
