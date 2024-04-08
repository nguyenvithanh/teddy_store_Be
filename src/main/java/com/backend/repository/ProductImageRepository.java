package com.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.model.ProductImage;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, String>{
     @Query(value = "SELECT pi.img_url " +
     "FROM PRODUCT p " +
     "JOIN PRODUCT_IMAGE pi ON pi.id_pro = p.id " +
     "WHERE p.id = :id", nativeQuery = true)
    List<Object[]> getProductImages(@Param("id") String id);
    
    @Query("SELECT a FROM ProductImage a ORDER BY a.id DESC LIMIT 1")
    Optional<ProductImage> findLastProductImage();
    
    @Query("SELECT a FROM ProductImage a WHERE a.product.id = :pid")
    List<ProductImageRepositoryCustom> findProductImageByProduct(@Param("pid") String id);
    interface ProductImageRepositoryCustom {
        String getId();
        String getImg_url();
    }
}

