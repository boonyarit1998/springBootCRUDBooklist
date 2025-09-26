package com.restapi.booklists.dto;

import com.restapi.booklists.entity.BookEntity;
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

    public static BookResponseDTO toDTO(BookEntity book){
        BookResponseDTO response = new BookResponseDTO();
        response.setName(book.getName());
        response.setDescription(book.getDescription());
        response.setCategory(CategoryResponseDTO.toDTO(book.getCategory()));
        return response;
    }
}
