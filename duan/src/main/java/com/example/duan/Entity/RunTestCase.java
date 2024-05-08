package com.example.duan.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "run_testcase")
@Data
public class RunTestCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "result")
    private String result;

    @Column(name = "runtime")
    private double runtime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dohomework_id")
    @JsonIgnore
    private DoHomeWork doHomeWork;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "testcase_id")
    @JsonIgnore
    private TestCase testCase;
}
