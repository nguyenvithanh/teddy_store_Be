package com.backend.payload;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductPayload {
    String id;
    String name;
    String description;
    String idCategory;
    String idColor;
    String idSize;
    String active;
    List<MultipartFile> images;
    Double price;
    Integer quantity;
}
