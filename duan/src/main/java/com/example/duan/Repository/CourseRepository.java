package com.example.duan.Repository;

import com.example.duan.Entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Optional<Course> findByCode(String code);

    @Query(value = "SELECT * FROM course WHERE name LIKE CONCAT('%', ?1, '%') ORDER BY id DESC", nativeQuery = true)
    Page<Course> findByNameOrderByIdDesc(String keyword, Pageable pageable);
}
