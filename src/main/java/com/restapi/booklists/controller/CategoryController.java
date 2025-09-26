package com.restapi.booklists.controller;

import com.restapi.booklists.dto.CategoryRequestDTO;
import com.restapi.booklists.dto.CategoryResponseDTO;
import com.restapi.booklists.dto.CommonResponse;
import com.restapi.booklists.dto.ErrorResponse;
import com.restapi.booklists.entity.BookEntity;
import com.restapi.booklists.entity.CategoryEntity;
import com.restapi.booklists.repository.CategoryRepository;
import com.restapi.booklists.service.CategoryService;
import com.restapi.booklists.utility.bookConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/category")
public class CategoryController implements bookConstant {

    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategory(){
        List<CategoryResponseDTO> category = categoryService.getAllCategory();
        return ResponseEntity.ok().body(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryByID(@PathVariable Long id){
        CategoryResponseDTO category = categoryService.getCategoryById(id);
        return ResponseEntity.ok().body(category);
    }

    @PostMapping()
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody CategoryRequestDTO category){
        CategoryResponseDTO newCategory = categoryService.createCategory(category);
        return ResponseEntity.ok().body(newCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable Long id,@RequestBody CategoryRequestDTO category){
        CategoryResponseDTO updateCategory = categoryService.updateCategory(id,category);
        return ResponseEntity.ok().body(updateCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        categoryService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
