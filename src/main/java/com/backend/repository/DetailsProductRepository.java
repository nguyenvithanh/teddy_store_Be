package com.backend.repository;
 
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.model.DetailsProduct;

@Repository
public interface DetailsProductRepository extends JpaRepository<DetailsProduct, String>{
 @Query(value = "SELECT p.id, pr.name, c.color, s.size_no, COUNT(o.id) as purchases " +
                   "FROM DETAILS_PRODUCT p " +
                   "JOIN [ORDER] o ON o.id = p.id " +
                   "JOIN PRODUCT pr ON pr.id = p.id_pro " +
                   "JOIN SIZE s ON s.id = p.id " +
                   "JOIN COLOR c ON c.id = p.id " +
                   "GROUP BY p.id, pr.name, c.color, s.size_no", nativeQuery = true)
    List<Object[]> getProductDetails();
    
  @Query(value="SELECT dp.id FROM DETAILS_PRODUCT dp " +
                       "JOIN COLOR c ON c.id = dp.id_color " +
                       "JOIN SIZE s ON s.id = dp.id_size " +
                       "JOIN DISCOUNT dc ON dc.id_dt_pro = dp.id " +
                       "WHERE s.id = ? AND c.id = ?",nativeQuery = true)
  List<Object[]> getProductDt(@Param("sizeId") String sizeId,@Param("colorId") String colorId );

  @Query("SELECT a FROM DetailsProduct a ORDER BY a.id DESC LIMIT 1")
  Optional<DetailsProduct> findLastDetailsProduct();
  
  @Query("SELECT a FROM DetailsProduct a WHERE a.product.id = :pid")
  Optional<DetailsProduct> findDetailsProductByProduct(@Param("pid") String id);

}
