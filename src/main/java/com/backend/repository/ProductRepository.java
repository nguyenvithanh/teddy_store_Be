package com.backend.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
 
import com.backend.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

	@Query(value = "SELECT TOP 8 p.id, p.name, MIN(pi.img_url) FROM Size s "
			+ "JOIN Details_Product dp ON dp.id_size = s.id "
			+ "JOIN Product p ON p.id = dp.id_pro "
			+ "JOIN Product_Image pi ON pi.id_pro = p.id "
			+ "GROUP BY p.id, p.name", nativeQuery = true)
	List<Object[]> findAllProducts();

	@Query(value = "SELECT p.id, p.name, MIN(pi.img_url) FROM Size s "
			+ "JOIN Details_Product dp ON dp.id_size = s.id "
			+ "JOIN Product p ON p.id = dp.id_pro "
			+ "JOIN Product_Image pi ON pi.id_pro = p.id "
			+ "JOIN Category c ON c.id = p.id_cate "
			+ "WHERE c.name = N'Thú bông' GROUP BY p.id, p.name", nativeQuery = true)
	List<Object[]> findAllWhereThuBong();
	
	@Query(value = "SELECT p.id, p.name, MIN(pi.img_url) FROM Size s "
			+ "JOIN Details_Product dp ON dp.id_size = s.id "
			+ "JOIN Product p ON p.id = dp.id_pro "
			+ "JOIN Product_Image pi ON pi.id_pro = p.id "
			+ "JOIN Category c ON c.id = p.id_cate "
			+ "WHERE c.name = N'Gấu bông hoạt hình' GROUP BY p.id, p.name", nativeQuery = true)
	List<Object[]> findAllWhereGauHoatHinh();

	@Query(value = "SELECT p.id, p.name, MIN(pi.img_url) FROM Size s "
			+ "JOIN Details_Product dp ON dp.id_size = s.id "
			+ "JOIN Product p ON p.id = dp.id_pro "
			+ "JOIN Product_Image pi ON pi.id_pro = p.id "
			+ "JOIN Category c ON c.id = p.id_cate "
			+ "WHERE p.id = :id GROUP BY p.id, p.name", nativeQuery = true) 
    List<Object[]> getProductDetails(@Param("id") String id);

    @Query("SELECT a FROM Product a ORDER BY a.id DESC LIMIT 1")
    Optional<Product> findLastProduct();
    
    @Query("""
            SELECT p 
            FROM Product p 
           
        """)
    List<ProductRepositoryCustom> findAllProductNoDiscount();


    @Query("SELECT a FROM Product a JOIN FETCH a.detailsProduct dp WHERE dp.active = :active")
    List<ProductRepositoryCustom> findAllByActive(@Param("active") Boolean active);

    @Query("""
                        SELECT p
                        FROM Product p
                         JOIN FETCH p.productImages
                         JOIN FETCH p.category
                         JOIN FETCH p.detailsProduct pd 
                         JOIN FETCH pd.color
                            JOIN FETCH pd.size
                            WHERE pd.active = true
            """)
    Page<ProductResponse> findAllProduct(Pageable pageable);
    
    Boolean existsByName(String name); 

    @Query("""
                        SELECT p
                        FROM Product p
                         JOIN FETCH p.productImages
                         JOIN FETCH p.category
                         JOIN FETCH p.detailsProduct pd
                         JOIN FETCH pd.color
                            JOIN FETCH pd.size
                            WHERE pd.active = true
                            AND p.name LIKE %:name%
            """)
    Page<ProductResponse> findAllProductByName(@Param("name") String name,Pageable pageable);

    // �?i t�?ng tr? v? c?a c�u query, vi?t h�m getter,  Mu?n l?y tr�?ng th�ng tin n�o th? vi?t h�m getter cho tr�?ng �� �? l?y
    interface ProductResponse {
        String getId();

        String getName();

        String getDescription();
        CategoryResponse getCategory();
        Set<ProductImageResponse> getProductImages();
        Set<DetailProductResponse> getDetailsProduct();

        interface ProductImageResponse {
            String getId();

            String getImg_url();
        }

        interface CategoryResponse {
            String getId();

            String getName();
        }

        interface DetailProductResponse {
            String getId();

            double getPrice();

            int getQuantity();

            SizeResponse getSize();

            ColorResponse getColor();
            Boolean getActive();

            interface SizeResponse {
                String getId();

                String getSize_no();
            }

            interface ColorResponse {
                String getId();
                String getColor();
            }
        }
    }

    interface ProductRepositoryCustom {
        String getId();
        String getName();
        Set<DetailProductResponse> getDetailsProduct();

        interface DetailProductResponse {
            String getId();
            Boolean getActive();
            double getPrice();
        }
    }
	
}
