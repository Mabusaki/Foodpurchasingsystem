package com.foodpurchasingsystem.foodpurchasingsystem.implementation;

import com.foodpurchasingsystem.foodpurchasingsystem.DTO.requestdto.CategoryDTOreq;
import com.foodpurchasingsystem.foodpurchasingsystem.DTO.responsedto.CategoryDTO;
import com.foodpurchasingsystem.foodpurchasingsystem.entity.Category;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.CategoryException;
import com.foodpurchasingsystem.foodpurchasingsystem.repository.CategoryRepo;
import com.foodpurchasingsystem.foodpurchasingsystem.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepo categoryRepo, ModelMapper modelMapper) {
        this.categoryRepo = categoryRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CategoryDTO> viewAllCategories() throws CategoryException {
        List<Category> categories = categoryRepo.findAll();
        List<CategoryDTO> categoriesDTO = modelMapper.map(categories, new TypeToken<List<CategoryDTO>>() {}.getType());
        if(categoriesDTO.size() > 0) return categoriesDTO;
        else throw new CategoryException("Categories not found");
    }

    @Override
    public CategoryDTO viewCategory(Integer categoryId) throws CategoryException {
        Optional<Category> optionalCategory = categoryRepo.findById(categoryId);
        if(optionalCategory.isPresent()) {
            CategoryDTO categoryDTO = modelMapper.map(optionalCategory.get(), CategoryDTO.class);
            return categoryDTO;
        }
        else throw new CategoryException("Category not found");
    }

    @Override
    public Category addCategory(CategoryDTOreq categoryDTOreq) throws CategoryException {
        Category cat = modelMapper.map(categoryDTOreq, Category.class);
        if(cat != null) {
            categoryRepo.save(cat);
            return cat;
        }else {
            throw new CategoryException("Fill minimum one field");
        }
    }

    @Override
    public Category updateCategory(CategoryDTOreq categoryDTOreq) throws CategoryException {
        Category category = modelMapper.map(categoryDTOreq, Category.class);
        if(categoryRepo.existsById(category.getCategoryId())){
            categoryRepo.save(category);
            return category;
        }else throw new CategoryException("Not found");
    }

    @Override
    public Category removeCategory(Integer categoryId) throws CategoryException {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new CategoryException("Category not found"));
        categoryRepo.delete(category);
        return category;
    }
}
