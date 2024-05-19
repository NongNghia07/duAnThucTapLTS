package com.example.duan.Repository;

import com.example.duan.Entity.CourseSubject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseSubjectRepository extends JpaRepository<CourseSubject, Integer> {
    CourseSubject findByCourse_IdAndSubject_Id(int courseId, int subjectId);
}
