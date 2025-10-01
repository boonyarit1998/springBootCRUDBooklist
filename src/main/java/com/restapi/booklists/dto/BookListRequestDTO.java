package com.restapi.booklists.dto;

import com.restapi.booklists.entity.ReadingStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookListRequestDTO {

    @NotBlank(message = "Booklist name is required")
    @Size(max = 100, message = "Booklist name must not exceed 100 characters")
    private String name;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    private List<BookListItemRequestDTO> item;
}
