package com.backend.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.dto.ProductDTO;
import com.backend.model.DetailsProduct;
import com.backend.model.Product;
import com.backend.model.ProductImage;
import com.backend.payload.ProductPayload;
import com.backend.repository.CategoryRepository;
import com.backend.repository.ColorRepository;
import com.backend.repository.DetailsProductRepository;
import com.backend.repository.ProductImageRepository;
import com.backend.repository.ProductRepository;
import com.backend.repository.SizeRepository;
import com.backend.services.ProductService;
import com.backend.util.CloudinaryUtil;
import com.backend.util.RandomUtil;
import com.cloudinary.utils.StringUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/teddy-store") 
@CrossOrigin(value="http://localhost:3000/")
public class ProductController {

	@Autowired
	private ProductService proService;
	@Autowired
    private ProductRepository proRepository;
	@Autowired
    private DetailsProductRepository detailsProductRepository;
	@Autowired
    private ProductImageRepository productImageRepository;
	@Autowired
    private CategoryRepository categoryRepository;
	@Autowired
    private ColorRepository colorRepository;
	@Autowired
    private SizeRepository sizeRepository;
	
	@GetMapping("/getAllProduct")
	public List<ProductDTO> getAllProduct(){
		return proService.getAllProductDTO();
	}
	
	@GetMapping("/getAllProductWhere-Thu-Bong")
	public List<ProductDTO> getProductWhereThuBong(){
		return proService.getAllProductWhereThuBong();
	}
	
	@GetMapping("/getAllProductWhere-Gau-Bong-Hoat-Hinh")
	public List<ProductDTO> getProductWhereGauHoatHinh(){
		return proService.getAllProductWhereGauHoatHinh();
	}
	@GetMapping("/getProductDetailID/{id}")
    public List<ProductDTO> getProductDetails(@PathVariable String id) {
        return proService.getProductDetailsById(id);
    }
	@GetMapping("/getAllProductV1")
    public Object getAllProduct(@RequestParam("page") int page, @RequestParam("size") int size) {
        return proService.getAllProduct(page, size);
    }

    @GetMapping("getAllProductActive")
    public Object getAllProductActive() {
        return proService.getAllProductActive();
    }

    @GetMapping("searchProduct")
    public Object searchProduct(@RequestParam("textSearch") String textSearch) {
        return proService.searchProductByName(textSearch);
    }
    @PatchMapping("deleteProduct")
    public Object deleteProduct(@RequestBody ProductPayload productPayload) {
        var product = proRepository.findById(productPayload.getId());
        if (product.isPresent()) {
//            product.get().setActive(Boolean.FALSE);
            proRepository.save(product.get());
            return "OK";
        }
        return "FAIL";
    }

    @PostMapping("updateProduct")
    public Object updateProduct(
            @RequestParam("description") String description,
            @RequestParam("idCate") String idCategory,
            @RequestParam("idColor") String idColor,
            @RequestParam("idSize") String idSize,
            @RequestParam("name") String name,
            @RequestParam("price") double price,
            @RequestParam("quantity") int quantity,
            @RequestParam("images") List<MultipartFile> images
    ) {
        var lastProduct = proRepository.findLastProduct();
        var product = new Product();
        if (lastProduct.isPresent()) {
            product.setId(RandomUtil.getNextId(lastProduct
                                                       .get()
                                                       .getId(), "PR"));
        } else {
            product.setId(RandomUtil.getNextId(null, "PR"));
        }
        product.setName(name);
        product.setDescription(description);
//        product.setActive(Boolean.TRUE);

        // set details product
        var lastDetailProduct = detailsProductRepository.findLastDetailsProduct();
        var detailProduct = new DetailsProduct();
        if (lastDetailProduct.isPresent()) {
            detailProduct.setId(RandomUtil.getNextId(lastDetailProduct
                                                             .get()
                                                             .getId(), "DP"));
        } else {
            detailProduct.setId(RandomUtil.getNextId(null, "DP"));
        }
        detailProduct.setPrice(price);
        detailProduct.setQuantity(quantity); 
        detailProduct.setActive(Boolean.TRUE);
        var category = categoryRepository.findById(idCategory);
        var color = colorRepository.findById(idColor);
        var size = sizeRepository.findById(idSize);
        category.ifPresent(product::setCategory);
        color.ifPresent(detailProduct::setColor);
        size.ifPresent(detailProduct::setSize);
        detailProduct.setProduct(product);
        product.setDetailsProduct(Collections.singleton(detailProduct));
        var lastProductImage = productImageRepository.findLastProductImage();
        AtomicReference<String> productImageId = new AtomicReference<>("");
        List<ProductImage> setImages = new ArrayList<>();
        images.forEach(el -> {
            var productImage = new ProductImage();
            if(StringUtils.isEmpty(productImageId.get())){
                if (lastProductImage.isPresent()) {
                    productImageId.set(RandomUtil.getNextId(lastProductImage
                                                                    .get()
                                                                    .getId(), "PI"));
                } else {
                    productImageId.set(RandomUtil.getNextId(null, "PI"));
                }
            }else {
                productImageId.set(RandomUtil.getNextId(productImageId.get(), "PI"));
            }
            productImage.setId(productImageId.get());
            try {
                var url = CloudinaryUtil.uploadImage(el.getBytes());
                productImage.setImg_url(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            productImage.setProduct(product);
            setImages.add(productImage);
        });

        // set images
        product.setProductImages(new HashSet<>(setImages));
        return proService.updateProduct(product);
    }
}