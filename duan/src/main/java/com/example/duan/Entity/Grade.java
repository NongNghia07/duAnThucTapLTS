package com.example.duan.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "grade")
@Data
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "grade")
    private Double grade;

    @Column(name = "feedback")
    private String feedback;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "do_homework_id")
    private DoHomeWork doHomeWork;
}
