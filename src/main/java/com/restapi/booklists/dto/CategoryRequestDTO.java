package com.restapi.booklists.dto;

import com.restapi.booklists.entity.CategoryEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequestDTO {

    @NotBlank(message = "Category name is required")
    @Size(max = 100, message = "Category name must not exceed 100 characters")
    private String category ;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    public static CategoryEntity toEntity(CategoryRequestDTO request){
        CategoryEntity entity = new CategoryEntity();
        entity.setCategory(request.getCategory());
        entity.setDescription(request.getDescription());
        return entity;
    }
}
