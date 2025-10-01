package com.restapi.booklists.controller;

import com.restapi.booklists.dto.BookRequestDTO;
import com.restapi.booklists.dto.BookResponseDTO;
import com.restapi.booklists.service.BookService;
import com.restapi.booklists.utility.bookConstant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/book")
@RequiredArgsConstructor
@Tag(name = "Book", description = "Book management APIs")
public class BookController implements bookConstant {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final BookService bookService;

    @GetMapping()
    @Operation(summary = "Get All book", description = "get all book detail")
    public ResponseEntity<List<BookResponseDTO>> getAllBooks() throws Exception{
        List<BookResponseDTO> book = bookService.getAllBooks();
        return ResponseEntity.ok().body(book);

    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Book By ID", description = "get Book detail by id")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long id) throws Exception{
        BookResponseDTO book = bookService.getBookById(id);
        return ResponseEntity.ok().body(book);
    }

    @PostMapping()
    @Operation(summary = "Create Book", description = "Create new Book ")
    public ResponseEntity<BookResponseDTO> createBook(@Valid  @RequestBody BookRequestDTO bookEntity) throws Exception{
        BookResponseDTO response = bookService.createBook(bookEntity);
        return ResponseEntity.ok().body(response);

    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Book", description = "Update Book by id")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable Long id,@Valid @RequestBody BookRequestDTO bookEdit) throws Exception{
        BookResponseDTO book = bookService.updateBook(id,bookEdit);
        return new ResponseEntity<>(book, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Book", description = "Delete Book by id")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) throws Exception{
            bookService.deleteBook(id);
         return ResponseEntity.ok().build();
    }

}
