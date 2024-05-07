package com.example.duan.Entity;

import com.example.duan.Enum.HomeWorkStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "do_home_work")
@Data
public class DoHomeWork implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private HomeWorkStatus homeWorkStatus;

    @Column(name = "is_finished")
    private boolean isFinished = false;

    @Column(name = "actual_output")
    private String actualOutput;

    @Column(name = "done_time")
    private LocalDateTime doneTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "practice_id")
    @JsonIgnore
    private Practice practice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "register_study_id")
    @JsonIgnore
    private RegisterStudy registerStudy;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doHomeWork")
    @JsonIgnore
    private Set<RunTestCase> runTestCases;
}
