package com.foodpurchasingsystem.foodpurchasingsystem.implementation;

import com.foodpurchasingsystem.foodpurchasingsystem.DTO.requestdto.ProductDTOreq;
import com.foodpurchasingsystem.foodpurchasingsystem.DTO.responsedto.ProductDTO;
import com.foodpurchasingsystem.foodpurchasingsystem.entity.Category;
import com.foodpurchasingsystem.foodpurchasingsystem.entity.Product;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.CategoryException;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.ProductException;
import com.foodpurchasingsystem.foodpurchasingsystem.repository.CategoryRepo;
import com.foodpurchasingsystem.foodpurchasingsystem.repository.ProductRepo;
import com.foodpurchasingsystem.foodpurchasingsystem.service.ProductService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepo productRepo, CategoryRepo categoryRepo, ModelMapper modelMapper) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductDTO> viewAllProduct() throws ProductException {
        List<Product> products = productRepo.findAll();
        List<ProductDTO> productDTOS = modelMapper.map(products, new TypeToken<List<ProductDTO>>() {}.getType());
        if(productDTOS.size() > 0) return productDTOS;
        else throw new ProductException("Products not found");
    }

    @Override
    public ProductDTO viewProduct(Integer productId) throws ProductException {
        Optional<Product> opt = productRepo.findById(productId);
        if(opt.isPresent()) {
            ProductDTO productDTO = modelMapper.map(opt.get(), ProductDTO.class);
            return productDTO;
        }
        else throw new ProductException("Product not found with given id");
    }

    @Override
    public List<ProductDTO> viewProductByCategory(Integer categoryId) throws ProductException, CategoryException {
        Optional<Category> optionalCategory = categoryRepo.findById(categoryId );
        List<ProductDTO> productDTOS = modelMapper.map(optionalCategory.get().getProductList(), new TypeToken<List<ProductDTO>>() {}.getType());
        if(optionalCategory.isPresent()) {
            return productDTOS;
            //return optionalCategory.get().getProductList();
        }
        else throw new ProductException("Product not found with given category");
    }

    @Override
    public Product addProduct(ProductDTOreq productDTOreq) throws ProductException {
        Product product = modelMapper.map(productDTOreq, Product.class);
        if(product != null){
            productRepo.save(product);
            return product;
        }else{
            throw new ProductException("Fill minimum one filed");
        }
    }

    @Override
    public Product updateProduct(ProductDTOreq productDTOreq) throws ProductException {
        Product product = modelMapper.map(productDTOreq, Product.class);
        if(productRepo.existsById(product.getProductId())){
            productRepo.save(product);
            return product;
        }else throw new ProductException("Product not found");
    }

    @Override
    public Product removeProduct(Integer productId) throws ProductException {
        Product product = productRepo.findById(productId).orElseThrow(() -> new ProductException("Product not found"));
        productRepo.delete(product);
        return product;
    }
}
