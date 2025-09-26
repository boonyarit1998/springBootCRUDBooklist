package com.restapi.booklists.dto;

import com.restapi.booklists.entity.ReadingStatus;

public class BookListResponseDTO {
    private BookResponseDTO bookId;
    private ReadingStatus status;
    private String name;
    private String description;
}
