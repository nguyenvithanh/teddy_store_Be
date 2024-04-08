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

import jakarta.transaction.Transactional;
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
    @Transactional
    public Object updateProduct(
            @RequestParam("id") String id,
            @RequestParam("description") String description,
            @RequestParam("idCate") String idCategory,
            @RequestParam("idColor") String idColor,
            @RequestParam("idSize") String idSize,
            @RequestParam("name") String name,
            @RequestParam("price") double price,
            @RequestParam("quantity") int quantity,
            @RequestParam(value = "listImageDelete", required = false) List<String> listImageDelete,
            @RequestParam(value = "images", required = false) List<MultipartFile> images
    ) {
        // kiem tra xem co trung ten khong
        
        var product = new Product();
        var detailProduct = new DetailsProduct();
        var lastProductImage = productImageRepository.findLastProductImage();
        var category = categoryRepository.findById(idCategory);
        var color = colorRepository.findById(idColor);
        var size = sizeRepository.findById(idSize);
        AtomicReference<String> productImageId = new AtomicReference<>("");
        if ("P-1".equalsIgnoreCase(id)) {
            var lastProduct = proRepository.findLastProduct();
            if (lastProduct.isPresent()) {
                product.setId(RandomUtil.getNextId(lastProduct
                                                           .get()
                                                           .getId(), "PR"));
            } else {
                product.setId(RandomUtil.getNextId(null, "PR"));
            }
            var lastDetailProduct = detailsProductRepository.findLastDetailsProduct();

            if (lastDetailProduct.isPresent()) {
                detailProduct.setId(RandomUtil.getNextId(lastDetailProduct
                                                                 .get()
                                                                 .getId(), "DP"));
            } else {
                detailProduct.setId(RandomUtil.getNextId(null, "DP"));
            }
            detailProduct.setStatus(Boolean.TRUE);
            detailProduct.setActive(Boolean.TRUE);

            List<ProductImage> setImages = new ArrayList<>();
            Product finalProduct = product;
            if (images != null && !images.isEmpty()) {
                images.forEach(el -> {
                    var productImage = new ProductImage();
                    if (StringUtils.isEmpty(productImageId.get())) {
                        if (lastProductImage.isPresent()) {
                            productImageId.set(RandomUtil.getNextId(lastProductImage
                                                                            .get()
                                                                            .getId(), "PI"));
                        } else {
                            productImageId.set(RandomUtil.getNextId(null, "PI"));
                        }
                    } else {
                        productImageId.set(RandomUtil.getNextId(productImageId.get(), "PI"));
                    }
                    productImage.setId(productImageId.get());
                    try {
                        var url = CloudinaryUtil.uploadImage(el.getBytes());
                        productImage.setImg_url(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    productImage.setProduct(finalProduct);
                    setImages.add(productImage);
                });
            }

            // set images
            product.setProductImages(new HashSet<>(setImages));
            detailProduct.setProduct(product);
        } else {
            product = proRepository
                    .findById(id)
                    .get();
            var dp = detailsProductRepository.findDetailsProductByProduct(id);
            if (dp.isPresent()) {
                detailProduct = dp.get();
            }
            var listImage = productImageRepository.findProductImageByProduct(id);
            // remove image in listImageDelete
            if (listImageDelete != null && !listImageDelete.isEmpty()) {
                listImage.forEach(el -> {
                    if (listImageDelete.contains(el.getId())) {
                        productImageRepository.deleteById(el.getId());
                    }
                });
            }
            // add new image
            Product finalProduct1 = product;
            if (images != null && !images.isEmpty()) {
                images.forEach(el -> {
                    var productImage = new ProductImage();
                    if (StringUtils.isEmpty(productImageId.get())) {
                        if (lastProductImage.isPresent()) {
                            productImageId.set(RandomUtil.getNextId(lastProductImage
                                                                            .get()
                                                                            .getId(), "PI"));
                        } else {
                            productImageId.set(RandomUtil.getNextId(null, "PI"));
                        }
                    } else {
                        productImageId.set(RandomUtil.getNextId(productImageId.get(), "PI"));
                    }
                    productImage.setId(productImageId.get());
                    try {
                        var url = CloudinaryUtil.uploadImage(el.getBytes());
                        productImage.setImg_url(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    productImage.setProduct(finalProduct1);
                    productImageRepository.save(productImage);
                });
            }
        }
        product.setName(name);
        product.setDescription(description);
        // set details product
        detailProduct.setPrice(price);
        detailProduct.setQuantity(quantity);


        category.ifPresent(product::setCategory);
        color.ifPresent(detailProduct::setColor);
        size.ifPresent(detailProduct::setSize);
        if ("P-1".equalsIgnoreCase(id)) {
            product.setDetailsProduct(Collections.singleton(detailProduct));
        } else {
            detailsProductRepository.save(detailProduct);
        }
        return proService.updateProduct(product);
    }

}