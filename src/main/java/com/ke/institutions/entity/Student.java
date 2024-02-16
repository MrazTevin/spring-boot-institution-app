package com.ke.institutions.entity;

import jakarta.persistence.*;

import javax.print.attribute.standard.MediaSize;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;


}
