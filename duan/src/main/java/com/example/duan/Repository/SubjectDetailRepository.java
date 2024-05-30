package com.example.duan.Repository;

import com.example.duan.Entity.SubjectDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectDetailRepository extends JpaRepository<SubjectDetail, Integer> {
    List<SubjectDetail> findBySubject_Id(int id);
}
