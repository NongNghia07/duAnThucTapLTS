package com.example.duan.Repository;

import com.example.duan.Entity.DoHomeWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoHomeWorkRepository extends JpaRepository<DoHomeWork, Integer>{
}
