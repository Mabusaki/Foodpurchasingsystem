package com.foodpurchasingsystem.foodpurchasingsystem.service;

import com.foodpurchasingsystem.foodpurchasingsystem.DTO.requestdto.CategoryDTOreq;
import com.foodpurchasingsystem.foodpurchasingsystem.DTO.responsedto.CategoryDTO;
import com.foodpurchasingsystem.foodpurchasingsystem.entity.Category;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.CategoryException;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> viewAllCategories() throws CategoryException;
    CategoryDTO viewCategory(Integer categoryId) throws CategoryException;
    Category addCategory(CategoryDTOreq category) throws CategoryException;
    Category updateCategory(CategoryDTOreq categoryDTOreq) throws CategoryException;
    Category removeCategory(Integer categoryId) throws CategoryException;
}
