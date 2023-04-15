package com.foodpurchasingsystem.foodpurchasingsystem.controller;

import com.foodpurchasingsystem.foodpurchasingsystem.DTO.requestdto.CategoryDTOreq;
import com.foodpurchasingsystem.foodpurchasingsystem.DTO.responsedto.CategoryDTO;
import com.foodpurchasingsystem.foodpurchasingsystem.entity.Category;
import com.foodpurchasingsystem.foodpurchasingsystem.exception.CategoryException;
import com.foodpurchasingsystem.foodpurchasingsystem.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("/viewall")
    @RolesAllowed({ "ROLE_USER", "ROLE_MODERATOR", "ROLE_ADMIN" })
    public ResponseEntity<List<CategoryDTO>> viewAllCategories() throws CategoryException {
        List<CategoryDTO> categoryDTOS = categoryService.viewAllCategories();
        return new ResponseEntity<List<CategoryDTO>>(categoryDTOS, HttpStatus.OK);
    }

    @GetMapping("/viewbyid/{categoryId}")
    @RolesAllowed({ "ROLE_USER", "ROLE_MODERATOR", "ROLE_ADMIN" })
    public ResponseEntity<CategoryDTO> viewCategoryById(@PathVariable Integer categoryId) throws CategoryException{
        return new ResponseEntity<CategoryDTO>(categoryService.viewCategory(categoryId), HttpStatus.OK);
    }

    @PostMapping("/add")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<Category> addCategory(@RequestBody CategoryDTOreq categoryDTOreq) throws CategoryException{
        return new ResponseEntity<Category>(categoryService.addCategory(categoryDTOreq), HttpStatus.OK);
    }

    @PutMapping("/update")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<Category> updateCategory(@RequestBody CategoryDTOreq categoryDTOreq) throws CategoryException{
        return new ResponseEntity<Category>(categoryService.updateCategory(categoryDTOreq), HttpStatus.OK);
    }

    @DeleteMapping("/remove/{cateogryId}")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<Category> removeCategory(@PathVariable Integer cateogryId) throws CategoryException {
        return new ResponseEntity<Category>(categoryService.removeCategory(cateogryId), HttpStatus.OK);
    }
}
