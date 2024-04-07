package com.backend.repository;
 
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.model.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, String>{
    @Query( value = "SELECT c.id,c.color , COUNT(c.color)" +
    "FROM DETAILS_PRODUCT dp " +
    "JOIN PRODUCT p ON p.id = dp.id_pro "+
    "JOIN COLOR c ON c.id = dp.id_color " +
    "WHERE p.id = :id GROUP BY  c.id,c.color" , nativeQuery=true)
    List<Object[]> getColorById(@Param("id") String id);
    
    @Query("SELECT a FROM Color a ORDER BY a.id DESC LIMIT 1")
    Optional<Color> findLastColor();
    @Query("SELECT c FROM Color c WHERE LOWER(c.color) LIKE %:color%")
    Page<Color> findAllByName(@Param("color") String color, PageRequest pageRequest);
}
