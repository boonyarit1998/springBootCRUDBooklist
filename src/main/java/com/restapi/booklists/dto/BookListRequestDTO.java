package com.restapi.booklists.dto;

import com.restapi.booklists.entity.ReadingStatus;
import lombok.Data;

@Data
public class BookListRequestDTO {
    private Long userId;
    private Long bookId;
    private ReadingStatus status;
    private String name;
    private String description;
}
