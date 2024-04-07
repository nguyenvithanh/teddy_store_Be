package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.model.Delivery;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, String>{

}
