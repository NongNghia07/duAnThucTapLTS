package com.example.duan.Repository;

import com.example.duan.Entity.CourseSubject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseSubjectRepository extends JpaRepository<CourseSubject, Integer> {
    CourseSubject findByCourse_IdAndSubject_Id(int courseId, int subjectId);
    List<CourseSubject> findByCourse_Id(int courseId);
}
