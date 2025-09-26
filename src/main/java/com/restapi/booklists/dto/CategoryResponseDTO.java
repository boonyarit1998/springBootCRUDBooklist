package com.restapi.booklists.dto;

import com.restapi.booklists.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDTO {
    private Long id;
    private String category ;
    private String description;

    public static CategoryResponseDTO toDTO(CategoryEntity category){
        CategoryResponseDTO response = new CategoryResponseDTO();
        response.setCategory(category.getCategory());
        response.setDescription(category.getDescription());
        response.setId(category.getId());
        return response;
    }
}
