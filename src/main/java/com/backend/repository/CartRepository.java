package com.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, String>{

	@Query(value="SELECT c.id_acc, dt.id, c.id, dt.price, S.name AS name_ser, P.name AS name_pro, CL.color, S.price AS price_ser, SI.size_no, c.quantity_pro, c.quantity_ser, c.date_add, MIN(a.img_url) AS image_pro, s.id\n" +
			"FROM CART c\n" +
			"JOIN DETAILS_PRODUCT dt on dt.id=c.id_dt_pro\n" +
			"JOIN PRODUCT p on p.id=dt.id_pro\n" +
			"JOIN PRODUCT_IMAGE a on a.id_pro=p.id\n" +
			"JOIN SERVICE S ON S.id = C.id_ser\n" +
			"JOIN SIZE SI ON SI.id = DT.id_size\n" +
			"JOIN COLOR CL ON CL.id = DT.id_color\n" +
			"WHERE c.id_acc=:id\n" +
			"GROUP BY c.id, c.id_acc, dt.id, dt.price, S.name, P.name, CL.color, S.price, SI.size_no, c.quantity_pro, c.quantity_ser, c.date_add, s.id\n",nativeQuery = true)
	public List<Object[]> findAllBy(@Param("id") String id);

}
