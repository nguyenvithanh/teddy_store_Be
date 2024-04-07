package com.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.model.AccountInfo;

@Repository
public interface AccountInfoRepository extends JpaRepository<AccountInfo, String> {

    @Query(value = "SELECT IA.ID, IA.AVATAR, IA.NAME, IA.GENDER, IA.EMAIL, IA.PHONE, MIN(AD.SUB_ADDRESS), IA.BIRTHDAY " +
            "FROM INFO_ACCOUNT IA " +
            "JOIN ACCOUNT A ON A.ID = IA.ID_ACC " +
            "JOIN [ADDRESS] AD ON AD.ID_ACC = A.ID " +
            "WHERE A.ID =:id " +
            "GROUP BY IA.ID, IA.AVATAR, IA.NAME, IA.GENDER, IA.EMAIL, IA.PHONE, IA.BIRTHDAY", nativeQuery = true)
    public List<Object[]> findDataAccWithId(@Param("id") String id);
    Optional<AccountInfo> findByEmail(String email);

    @Query("SELECT a FROM AccountInfo a ORDER BY a.id DESC LIMIT 1")
    Optional<AccountInfo> findLastAccountInfor();
}
	