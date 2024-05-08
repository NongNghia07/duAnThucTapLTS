package com.example.duan.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "course")
@Data
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "introduce")
    private String introduce;

    @Column(name = "image_course")
    private String imageCourse;

    @Column(name = "code")
    private String code;

    @Column(name = "price")
    private double price;

    @Column(name = "total_course_duration")
    private int totalCourseDuration;

    @Column(name = "number_of_student")
    private int numberOfStudent;

    @Column(name = "number_of_purchases")
    private int numberOfPurchases;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    @JsonIgnore
    private User creator; // người tạo khóa học, người này phải có quyền giảng viên thông qua chứng chỉ giảng viên ở bảng certificate

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    @JsonIgnore
    private Set<CourseSubject> courseSubjects;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    @JsonIgnore
    private Set<Bill> bills;
}
