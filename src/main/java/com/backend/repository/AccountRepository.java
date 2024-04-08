package com.backend.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.model.Account;

import jakarta.persistence.Column;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
	Optional<Account> findByUsername(String username);

	@Query("SELECT a FROM Account a ORDER BY a.id DESC LIMIT 1")
	Optional<Account> findLastAccount();

	@Query("""
					SELECT a
					FROM Account a
					INNER JOIN a.accountInfo ai
					WHERE ai.email = :email
			""")
	Optional<Account> findByEmail(String email);
	
	@Query("SELECT a FROM Account a WHERE a.username = :username")
	Optional<AccountResponse> getByUsername(@Param("username") String username);

	interface AccountResponse {
		String getUsername();

		String getPassword();

		Boolean getRole();

		Boolean getActive();
	}
}
