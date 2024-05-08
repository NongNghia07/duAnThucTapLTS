package com.example.duan.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "programing_language")
@Data
public class ProgramingLanguage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "language_name")
    private String languageName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "programingLanguage")
    @JsonIgnore
    private Set<Practice> practices;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "programingLanguage")
    @JsonIgnore
    private Set<TestCase> testCases;
}
