package com.restapi.booklists.controller;

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
    public ResponseEntity<Object> getAllCategory(){
        CommonResponse commonResponse = new CommonResponse();
        try {
            List<CategoryEntity> category = categoryService.getAllCategory();
            commonResponse.setStatus(STATUS_SUCCESS);
            commonResponse.setResultData(category);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(STATUS_ERROR,e.getMessage());
            return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCategoryByID(@PathVariable Long id){
        CategoryEntity category = categoryService.getCategoryById(id);
        return ResponseEntity.ok().body(category);
    }

    @PostMapping()
    public ResponseEntity<Object> createCategory(@RequestBody CategoryEntity category){
        CategoryEntity newCategory = categoryService.createCategory(category);
        return ResponseEntity.ok().body(newCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable Long id,@RequestBody CategoryEntity category){
        CategoryEntity updateCategory = categoryService.updateCategory(id,category);
        return ResponseEntity.ok().body(updateCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        categoryService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
