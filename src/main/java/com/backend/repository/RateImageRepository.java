package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.model.RateImage;

@Repository
public interface RateImageRepository extends JpaRepository<RateImage, String>{

}
