package com.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.model.Rate;

@Repository
public interface RateRepository extends JpaRepository<Rate, String>{
    @Query(value = "SELECT r.star_no, COUNT(r.id) AS Number_rate, COUNT(od.quantity_pro) AS Quantity " + 
               "FROM DETAILS_PRODUCT dp " +
               "JOIN PRODUCT p ON p.id = dp.id_pro " +
               "JOIN RATE r ON r.id_dt_pro = dp.id " +
               "JOIN DETAILS_ORDER od ON dp.id = od.id_ord " +
               "WHERE p.id = :id GROUP BY r.star_no", nativeQuery = true)
List<Object[]> getRateProduct(@Param("id") String id);

}
