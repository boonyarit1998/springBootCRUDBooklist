package com.restapi.booklists.controller;

import com.restapi.booklists.dto.BookListRequestDTO;
import com.restapi.booklists.dto.BookListResponseDTO;
import com.restapi.booklists.dto.CommonResponse;
import com.restapi.booklists.dto.ErrorResponse;
import com.restapi.booklists.entity.BookEntity;
import com.restapi.booklists.entity.BookListEntity;
import com.restapi.booklists.entity.ReadingStatus;
import com.restapi.booklists.entity.UserEntity;
import com.restapi.booklists.service.BookListService;
import com.restapi.booklists.service.BookService;
import com.restapi.booklists.service.UserService;
import com.restapi.booklists.utility.bookConstant;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/booklist")
public class BookListController implements bookConstant {


    private final BookListService bookListService;

    @GetMapping()
    public ResponseEntity<List<BookListResponseDTO>> getAllBookLists() throws Exception{
        List<BookListResponseDTO> response  = bookListService.getAllBookLists();
        return ResponseEntity.ok().body(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<BookListResponseDTO> getBooklistById(@PathVariable Long id) throws  Exception{
        BookListResponseDTO response = bookListService.getBookListById(id);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping()
    public ResponseEntity<BookListResponseDTO> createBookList(@RequestBody BookListRequestDTO book) throws  Exception{
        BookListResponseDTO response = bookListService.createBookList(book);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/{id}/addbook/{bookId}")
    public ResponseEntity<BookListResponseDTO> addBookToBookList(@PathVariable Long id, @PathVariable Long bookId , @RequestBody ReadingStatus status) throws  Exception{
        BookListResponseDTO bookList = bookListService.addBookToBooklist(id,bookId,status);
        return ResponseEntity.ok().body(bookList);

    }

    @PutMapping("/{id}")
    public ResponseEntity<BookListResponseDTO> addBookToBookList(@PathVariable Long id, @RequestBody BookListRequestDTO bookList) throws  Exception{
        BookListResponseDTO response = bookListService.updateBooklist(id,bookList);
        return ResponseEntity.ok().body(response);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) throws Exception{
        bookListService.deleteBookList(id);
        return ResponseEntity.ok().build();
    }


}
