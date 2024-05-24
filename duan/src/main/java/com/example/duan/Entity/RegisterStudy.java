package com.example.duan.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "register_study")
@Data
public class RegisterStudy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "is_finished")
    private boolean isFinished = false;

    @Column(name = "register_time")
    private LocalDateTime registerTime;

    @Column(name = "percent_complete")
    private int percentComplete;

    @Column(name = "done_time")
    private LocalDateTime doneTime;

    @Column(name = "is_active")
    private boolean isActive = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_subject_id")
    @JsonIgnore
    private Subject currentSubject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course course;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "registerStudy")
    @JsonIgnore
    private Set<DoHomeWork> doHomeWorks;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "registerStudy")
    @JsonIgnore
    private Set<LearningProgress> learningProgresses;
}
