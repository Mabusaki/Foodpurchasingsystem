package com.foodpurchasingsystem.foodpurchasingsystem.controller;

import com.foodpurchasingsystem.foodpurchasingsystem.DTO.requestdto.ProductDTOreq;
import com.foodpurchasingsystem.foodpurchasingsystem.DTO.responsedto.ProductDTO;
import com.foodpurchasingsystem.foodpurchasingsystem.entity.Product;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.CategoryException;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.ProductException;
import com.foodpurchasingsystem.foodpurchasingsystem.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/products")

public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/viewall")
    @RolesAllowed({ "ROLE_USER", "ROLE_MODERATOR", "ROLE_ADMIN" })
    public ResponseEntity<List<ProductDTO>> viewAllProducts() throws ProductException {
        return new ResponseEntity<List<ProductDTO>>(productService.viewAllProduct(), HttpStatus.OK);
    }
    @RolesAllowed({ "ROLE_USER", "ROLE_MODERATOR", "ROLE_ADMIN" })
    @GetMapping("/view/{productId}")
    public ResponseEntity<ProductDTO> viewProductById(@PathVariable("productId") Integer productId) throws ProductException{
        return new ResponseEntity<ProductDTO>(productService.viewProduct(productId), HttpStatus.OK);
    }
    @RolesAllowed({ "ROLE_USER", "ROLE_MODERATOR", "ROLE_ADMIN" })
    @GetMapping("/viewbycategory/{categoryId}")
    public ResponseEntity<List<ProductDTO>> viewProductsByCategoryId(@PathVariable("categoryId") Integer categoryId) throws ProductException, CategoryException {
        return new ResponseEntity<List<ProductDTO>>(productService.viewProductByCategory(categoryId), HttpStatus.OK);
    }
    @RolesAllowed("ROLE_ADMIN")
    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody ProductDTOreq product) throws ProductException{
        Product newProduct = productService.addProduct(product);
        return new ResponseEntity<Product>(newProduct, HttpStatus.OK);
    }
    @RolesAllowed("ROLE_ADMIN")
    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductDTOreq productDTOreq) throws ProductException{
        Product productToUpdate = productService.updateProduct(productDTOreq);
        return new ResponseEntity<Product>(productToUpdate, HttpStatus.OK);
    }
    @RolesAllowed("ROLE_ADMIN")
    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<Product> removeProductById(@PathVariable("productId") Integer productId) throws ProductException{
        return new ResponseEntity<Product>(productService.removeProduct(productId), HttpStatus.OK);
    }
}
