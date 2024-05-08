package com.example.duan.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "testcase")
@Data
public class TestCase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "input")
    private String input;

    @Column(name = "output")
    private String output;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "programing_language_id")
    @JsonIgnore
    private ProgramingLanguage programingLanguage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "practice_id")
    @JsonIgnore
    private Practice practice;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "testCase")
    @JsonIgnore
    private Set<RunTestCase> runTestCases;
}
