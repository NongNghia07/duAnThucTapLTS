package com.example.duan.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "subject_detail")
@Data
public class SubjectDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_finished")
    private boolean isFinished = false;

    @Column(name = "is_active")
    private boolean isActive = false;

    @Column(name = "link_video")
    private String linkVideo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    @JsonIgnore
    private Subject subject;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subjectDetail")
    @JsonIgnore
    private Set<Practice> practices;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subjectDetail")
    @JsonIgnore
    private Set<MakeQuestion> makeQuestions;
}
