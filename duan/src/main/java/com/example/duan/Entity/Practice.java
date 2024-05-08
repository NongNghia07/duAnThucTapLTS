package com.example.duan.Entity;

import com.example.duan.Enum.Level;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "practice")
@Data
public class Practice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "practice_code")
    private String practiceCode;

    @Enumerated(EnumType.STRING)
    private Level level;

    @Column(name = "title")
    private String title;

    @Column(name = "topic")
    private String topic;

    @Column(name = "expect_output")
    private String expect_output;

    @Column(name = "is_required")
    private boolean is_required = false;

    @Column(name = "is_deleted")
    private boolean is_deleted = false;

    @Column(name = "medium_score")
    private double medium_score;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_programing_id")
    @JsonIgnore
    private ProgramingLanguage programingLanguage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_detail_id")
    @JsonIgnore
    private SubjectDetail subjectDetail;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "practice")
    @JsonIgnore
    private Set<DoHomeWork> doHomeWorks;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "practice")
    @JsonIgnore
    private Set<TestCase> testCases;
}
