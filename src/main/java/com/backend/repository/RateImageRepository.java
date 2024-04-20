package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.model.RateImage;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RateImageRepository extends JpaRepository<RateImage, String>{
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM RATE_IMAGE WHERE id_rate = :idRate", nativeQuery = true)
    public void deleteByIdRate(String idRate);
}
