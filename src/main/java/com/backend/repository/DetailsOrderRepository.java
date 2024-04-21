package com.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.model.DetailsOrder;

@Repository
public interface DetailsOrderRepository extends JpaRepository<DetailsOrder, String>{
    @Query(value = "SELECT TOP 5 o.date_order, ord.id, o.status,(ord.price_unit + ord.price_unit_ser)" +
    "FROM DETAILS_ORDER ord " +
    "JOIN [ORDER] o ON o.id = ord.id_ord " +
    "ORDER BY o.date_order DESC", nativeQuery = true)
    List<Object[]> getLatestOrderss();
}
