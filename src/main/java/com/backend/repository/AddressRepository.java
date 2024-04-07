package com.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {

    @Query(value = "SELECT * FROM ADDRESS WHERE ID_ACC=:id", nativeQuery = true)
    public List<Address> findAllById(@Param("id") String id);

    @Query("SELECT a FROM Address a ORDER BY a.id DESC LIMIT 1")
    Optional<Address> findLastAddress();
}
