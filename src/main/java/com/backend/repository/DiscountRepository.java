package com.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.model.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, String> {
//    @Query(value = "SELECT dp.price, dc.price_sale, dp.quantity " +
//    "FROM DISCOUNT dc   " +
//    "JOIN DETAILS_PRODUCT dp ON dc.id_dt_pro = dp.id " +
//    "WHERE dp.id = :id",nativeQuery=true)
//    List<Object[]> getDiscount(@Param("id") String id);
	@Query(value = "SELECT dp.id FROM DETAILS_PRODUCT dp " + "JOIN COLOR c ON c.id = dp.id_color "
			+ "JOIN SIZE s ON s.id = dp.id_size " + "JOIN DISCOUNT dc ON dc.id_dt_pro = dp.id "
			+ "WHERE s.id = ? AND c.id = ?", nativeQuery = true)
	List<Object[]> getProductDt(@Param("sizeId") String sizeId, @Param("colorId") String colorId);

	@Query("SELECT a FROM Discount a ORDER BY a.id DESC LIMIT 1")
	Optional<Discount> findLastDiscount();

	@Query("""
			   SELECT d
			   FROM Discount d
			   JOIN FETCH d.detailsProduct dp
			   JOIN FETCH dp.product
			""")
	Page<DiscountResponse> findAllDiscount(PageRequest pageRequest);

	@Query("""
			   SELECT d
			   FROM Discount d
			   JOIN FETCH d.detailsProduct dp
			   JOIN FETCH dp.product
			   WHERE d.date_start >= :searchDateFrom AND d.date_end <= :searchDateTo
			""")
	Page<DiscountResponse> searchDiscountFromTo(java.sql.Date searchDateFrom, java.sql.Date searchDateTo,
			PageRequest pageRequest);

	interface DiscountResponse {
		String getId();

		Double getPrice_sale();

		String getDate_start();

		String getDate_end();

		DetailsProduct getDetailsProduct();

		interface DetailsProduct {
			String getId();

			Double getPrice();

			Product getProduct();

			interface Product {
				String getId();

				String getName();
			}
		}
	}
}
