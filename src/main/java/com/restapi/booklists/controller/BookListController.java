package com.restapi.booklists.controller;

import com.restapi.booklists.dto.BookListRequestDTO;
import com.restapi.booklists.dto.BookListResponseDTO;
import com.restapi.booklists.entity.ReadingStatus;
import com.restapi.booklists.service.BookListService;
import com.restapi.booklists.utility.bookConstant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/booklist")
@Tag(name = "BookList", description = "BookList management APIs")
public class BookListController implements bookConstant {


    private final BookListService bookListService;

    @GetMapping()
    @Operation(summary = "Get All BookList", description = "get all BookList detail")
    public ResponseEntity<List<BookListResponseDTO>> getAllBookLists() throws Exception{
        List<BookListResponseDTO> response  = bookListService.getAllBookLists();
        return ResponseEntity.ok().body(response);

    }

    @GetMapping("/{id}")
    @Operation(summary = "Get BookList By ID", description = "get BookList detail by id")
    public ResponseEntity<BookListResponseDTO> getBooklistById(@PathVariable Long id) throws  Exception{
        BookListResponseDTO response = bookListService.getBookListById(id);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping()
    @Operation(summary = "Create BookList", description = "Create new BookList ")
    public ResponseEntity<BookListResponseDTO> createBookList(@Valid  @RequestBody BookListRequestDTO book) throws  Exception{
        BookListResponseDTO response = bookListService.createBookList(book);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/{id}/addbook/{bookId}")
    @Operation(summary = "Add Book", description = "Add book into booklist")
    public ResponseEntity<BookListResponseDTO> addBookToBookList(@PathVariable Long id, @PathVariable Long bookId , @RequestBody ReadingStatus status) throws  Exception{
        BookListResponseDTO bookList = bookListService.addBookToBooklist(id,bookId,status);
        return ResponseEntity.ok().body(bookList);

    }

    @PutMapping("/{id}")
    @Operation(summary = "Update BookList", description = "Update BookList by id")
    public ResponseEntity<BookListResponseDTO> updateToBookList(@PathVariable Long id, @Valid @RequestBody BookListRequestDTO bookList) throws  Exception{
        BookListResponseDTO response = bookListService.updateBooklist(id,bookList);
        return ResponseEntity.ok().body(response);

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete BookList", description = "Update BookList by id")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) throws Exception{
        bookListService.deleteBookList(id);
        return ResponseEntity.ok().build();
    }


}
