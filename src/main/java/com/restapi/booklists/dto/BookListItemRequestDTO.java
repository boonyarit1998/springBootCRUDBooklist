package com.restapi.booklists.dto;

import com.restapi.booklists.entity.ReadingStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookListItemRequestDTO {
    @NotNull
    private Long bookId;

    @NotNull
    private ReadingStatus status;
}