package com.example.duan.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "learning_progress")
@Data
public class LearningProgress implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "register_study_id")
    private RegisterStudy registerStudy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_subject_id")
    private Subject currentSubject;
}
