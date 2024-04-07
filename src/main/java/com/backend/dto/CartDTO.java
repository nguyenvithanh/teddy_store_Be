package com.backend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CartDTO {
    private String id;
    private String id_acc;
    private String id_dt_pro;
    private String id_ser;
    private String name_ser;
    private String name_pro;
    private String size_no;
    private String image_pro;
    private String color;
    private Integer price_pro;
    private Integer price_ser;
    private Integer quantity_pro;
    private Integer quantity_ser;
    private Date date_add;

}
