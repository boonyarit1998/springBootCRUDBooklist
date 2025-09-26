package com.restapi.booklists.dto;

import com.restapi.booklists.entity.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDTO {
    private String name;
    private String description;
    private Long categoryId;

}
