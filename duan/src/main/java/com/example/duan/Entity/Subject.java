package com.example.duan.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "subject")
@Data
public class Subject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "is_active")
    private boolean isActive = false;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subject")
    @JsonIgnore
    private Set<SubjectDetail> subjectDetails;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "currentSubject")
    @JsonIgnore
    private Set<LearningProgress> learningProgresses;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "currentSubject")
    @JsonIgnore
    private Set<RegisterStudy> registerStudies;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subject")
    @JsonIgnore
    private Set<CourseSubject> courseSubjects;
}
