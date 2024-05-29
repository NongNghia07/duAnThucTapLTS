package com.example.duan.Repository;

import com.example.duan.Entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WardRepository extends JpaRepository<Ward, Integer> {
    Ward findByDistrict_Id(int districtId);
}
