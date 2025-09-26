package com.restapi.booklists.dto;

import com.restapi.booklists.entity.CategoryEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequestDTO {

    private String category ;

    private String description;

    public static CategoryEntity toEntity(CategoryRequestDTO request){
        CategoryEntity entity = new CategoryEntity();
        entity.setCategory(request.getCategory());
        entity.setDescription(request.getDescription());
        return entity;
    }
}
