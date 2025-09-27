package com.restapi.booklists.dto;

import com.restapi.booklists.entity.BookListEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookListResponseDTO {
    private Long bookListId;
    private String name;
    private String description;
    private List<BookListItemResponseDTO> item;

    public static BookListResponseDTO toDTO(BookListEntity entity){
        BookListResponseDTO response = new BookListResponseDTO();
        response.setBookListId(entity.getId());
        response.setName(entity.getName());
        response.setDescription(entity.getDescription());
        response.setItem(entity.getItems().stream().map(BookListItemResponseDTO::toDTO).toList());
        return response;
    }
}
