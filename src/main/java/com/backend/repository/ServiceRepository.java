package com.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.model.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, String>{
        @Query(value = "SELECT s.name, s.price, COUNT(*) AS product_count , s.id " +
        "FROM CATEGORY c " +
        "JOIN PRODUCT p ON p.id_cate = c.id " +
        "JOIN DETAILS_PRODUCT dp ON dp.id_pro = p.id " +
        "JOIN SERVICE s ON s.id = c.id " +
        "WHERE p.id = ? " +
        "GROUP BY s.name, s.price,s.id ", nativeQuery = true)
        List<Object[]>getProService(@Param("id") String id);
        
        boolean existsByNameAndIdIsNot(String name, String id);

        @Query("SELECT s FROM Service s order by s.id desc limit 1")
        Optional<Service> findLastService();

        @Query("SELECT s FROM Service s WHERE LOWER(s.name) LIKE %:name%")
        Page<Service> searchByName(@Param("name") String name, Pageable pageable);

        interface ServiceResponse {
            String getId();

            String getName();

            String getPrice();

            String getImage();

            String getDescription();

            CategoryResponse getCategory();

            interface CategoryResponse {
                String getId();

                String getName();
            }
        }
}
