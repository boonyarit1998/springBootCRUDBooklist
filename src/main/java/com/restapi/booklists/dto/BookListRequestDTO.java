package com.restapi.booklists.dto;

import com.restapi.booklists.entity.ReadingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookListRequestDTO {
    private String name;
    private String description;
    private List<BookListItemRequestDTO> item;
}
