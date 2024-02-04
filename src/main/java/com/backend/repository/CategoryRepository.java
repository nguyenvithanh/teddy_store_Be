package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

}
