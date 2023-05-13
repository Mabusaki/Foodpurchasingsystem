package com.foodpurchasingsystem.foodpurchasingsystem.DTO.requestdto;

import lombok.Data;

@Data
public class ProductDTOreq {
    private Integer productId;
    private String productName;
    private Double price;
    private Integer initialQuantity;
    private String filepath;
    private Integer categoryId;
}
