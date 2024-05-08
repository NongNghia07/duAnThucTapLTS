package com.example.duan.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "make_question")
@Data
public class MakeQuestion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "question")
    private String question;

    @Column(name = "number_of_answers")
    private int number_of_answers;

    @Column(name = "create_time")
    private LocalDateTime create_time;

    @Column(name = "update_time")
    private LocalDateTime update_time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_detail_id")
    @JsonIgnore
    private SubjectDetail subjectDetail;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
    @JsonIgnore
    private Set<Answers> answers;
}
