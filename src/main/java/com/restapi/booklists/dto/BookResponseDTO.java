package com.restapi.booklists.dto;

import com.restapi.booklists.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDTO {

    private String name;
    private String description;
    private CategoryResponseDTO category;
}
