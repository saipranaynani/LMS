package com.sdp3.main.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import com.sdp3.main.entity.Material;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface MaterialRepo extends JpaRepository<Material, Integer>{

    @Query("FROM Material as m WHERE m.courseCode =:id")
    List<Material> findMaterialsByCourseCode(@Param("id")Long id);
    
}
