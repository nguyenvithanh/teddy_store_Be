package com.backend.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Query("SELECT a FROM Account a JOIN FETCH a.accountInfo ai WHERE (a.id like %:query% " +
			"or name like %:query%) and a.active = :active and a.role = false")
	public Page<Account> getAccountSearchAndPagination(String query, Boolean active, Pageable pageable);
}
