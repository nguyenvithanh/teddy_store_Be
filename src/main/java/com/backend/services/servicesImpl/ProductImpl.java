package com.backend.services.servicesImpl;

//import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.backend.dto.ProductDTO;
import com.backend.model.Product;
import com.backend.repository.ProductRepository;
import com.backend.services.ProductService;

@Service
public class ProductImpl implements ProductService {

	@Autowired
	ProductRepository proRepository;

	@Override
	public List<Product> getAllProduct() {
		return proRepository.findAll();
	}

	private List<ProductDTO> convertToProductsDTO(List<Object[]> results) {
		List<ProductDTO> productDetailsDTOList = new ArrayList<>();

		for (Object[] result : results) {
			ProductDTO productDetailsDTO = new ProductDTO();
			productDetailsDTO.setId((String) result[0]);
			productDetailsDTO.setName((String) result[1]);
			productDetailsDTO.setImg_url((String) result[2]);

			productDetailsDTOList.add(productDetailsDTO);
		}

		return productDetailsDTOList;
	}

	public List<ProductDTO> getAllProductDTO() {
		List<Object[]> results = proRepository.findAllProducts();
		return convertToProductsDTO(results);
	}
	
	public List<ProductDTO> getAllProductWhereThuBong() {
		List<Object[]> results = proRepository.findAllWhereThuBong();
		return convertToProductsDTO(results);
	}

	public List<ProductDTO> getAllProductWhereGauHoatHinh() {
		List<Object[]> results = proRepository.findAllWhereGauHoatHinh();
		return convertToProductsDTO(results);
	}

	public List<ProductDTO> getProductDetailsById(String id) {
		List<Object[]> results = proRepository.getProductDetails(id);
		return convertToProductsDTO(results);
	}

	@Override
    public Object getAllProduct(int page, int size) {
        Pageable pageable =  PageRequest.of(page, size);
        return proRepository.findAllProduct( pageable);
    }

    @Override
    public Object getAllProductActive() {
        return proRepository.findAllByActive(true);
    }

    @Override
    public Object updateProduct(Product product) {
        proRepository.save(product);
        return "OK";
    }

    @Override
    public Object searchProductByName(String name) {
        return proRepository.findAllProductByName(name, PageRequest.of(0, 10));
    }


	

	
	 
}
