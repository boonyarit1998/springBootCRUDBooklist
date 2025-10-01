package com.restapi.booklists.controller;

import com.restapi.booklists.dto.CategoryRequestDTO;
import com.restapi.booklists.dto.CategoryResponseDTO;
import com.restapi.booklists.service.CategoryService;
import com.restapi.booklists.utility.bookConstant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/category")
@Tag(name = "Category", description = "Category management APIs")
public class CategoryController implements bookConstant {

    private final CategoryService categoryService;

    @GetMapping()
    @Operation(summary = "Get All Category", description = "get all Category detail")
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategory(){
        List<CategoryResponseDTO> category = categoryService.getAllCategory();
        return ResponseEntity.ok().body(category);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Category By ID", description = "get Category detail by id")
    public ResponseEntity<CategoryResponseDTO> getCategoryByID(@PathVariable Long id){
        CategoryResponseDTO category = categoryService.getCategoryById(id);
        return ResponseEntity.ok().body(category);
    }

    @PostMapping()
    @Operation(summary = "Create Category", description = "Create new Category ")
    public ResponseEntity<CategoryResponseDTO> createCategory(@Valid  @RequestBody CategoryRequestDTO category){
        CategoryResponseDTO newCategory = categoryService.createCategory(category);
        return ResponseEntity.ok().body(newCategory);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Category", description = "Update Category by id")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable Long id,@Valid @RequestBody CategoryRequestDTO category){
        CategoryResponseDTO updateCategory = categoryService.updateCategory(id,category);
        return ResponseEntity.ok().body(updateCategory);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Category", description = "Delete Category by id")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        categoryService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
