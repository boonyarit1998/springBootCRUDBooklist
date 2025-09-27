package com.restapi.booklists.dto;

import com.restapi.booklists.entity.BookListItemEntity;
import com.restapi.booklists.entity.ReadingStatus;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookListItemResponseDTO {
    private Long bookId;
    private String bookName;
    private ReadingStatus status;

    public static BookListItemResponseDTO toDTO(BookListItemEntity entity){
        BookListItemResponseDTO response = new BookListItemResponseDTO();
        response.setBookId(entity.getBook().getId());
        response.setBookName(entity.getBook().getName());
        response.setStatus(entity.getStatus());
        return  response;
    }
}
