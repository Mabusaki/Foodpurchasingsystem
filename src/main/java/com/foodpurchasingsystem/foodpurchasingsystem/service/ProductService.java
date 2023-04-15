package com.foodpurchasingsystem.foodpurchasingsystem.service;

import com.foodpurchasingsystem.foodpurchasingsystem.DTO.requestdto.ProductDTOreq;
import com.foodpurchasingsystem.foodpurchasingsystem.DTO.responsedto.ProductDTO;
import com.foodpurchasingsystem.foodpurchasingsystem.entity.Product;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.CategoryException;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.ProductException;

import java.util.List;

public interface ProductService {

    List<ProductDTO> viewAllProduct() throws ProductException;
    ProductDTO viewProduct(Integer productId) throws ProductException;
    List<ProductDTO> viewProductByCategory(Integer categoryId) throws ProductException, CategoryException;
    Product addProduct(ProductDTOreq productDTOreq) throws ProductException;
    Product updateProduct(ProductDTOreq productDTOreq) throws ProductException;
    Product removeProduct(Integer productId) throws ProductException;
}
