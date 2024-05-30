package com.example.duan.Repository;

import com.example.duan.Entity.ProgramingLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramingLanguageRepository extends JpaRepository<ProgramingLanguage, Integer>{
}
