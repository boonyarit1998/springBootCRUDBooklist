package com.restapi.booklists.controller;

import com.restapi.booklists.dto.BookRequestDTO;
import com.restapi.booklists.dto.BookResponseDTO;
import com.restapi.booklists.entity.BookEntity;
import com.restapi.booklists.dto.CommonResponse;
import com.restapi.booklists.dto.ErrorResponse;
import com.restapi.booklists.service.BookService;
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
@RequestMapping("api/book")
@RequiredArgsConstructor
public class BookController implements bookConstant {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final BookService bookService;

    @GetMapping()
    public ResponseEntity<List<BookResponseDTO>> getAllBooks() throws Exception{
        List<BookResponseDTO> book = bookService.getAllBooks();
        return ResponseEntity.ok().body(book);

    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long id) throws Exception{
        BookResponseDTO book = bookService.getBookById(id);
        return ResponseEntity.ok().body(book);
    }

    @PostMapping()
    public ResponseEntity<BookResponseDTO> createBook(@RequestBody BookRequestDTO bookEntity) throws Exception{
        BookResponseDTO response = bookService.createBook(bookEntity);
        return ResponseEntity.ok().body(response);

    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable Long id,@RequestBody BookRequestDTO bookEdit) throws Exception{
        BookResponseDTO book = bookService.updateBook(id,bookEdit);
        return new ResponseEntity<>(book, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) throws Exception{
            bookService.deleteBook(id);
         return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchBook(@RequestParam(required = false) String name,@RequestParam(required = false) String description ) throws Exception{
        logger.info("start searchBook");
        CommonResponse commonResponse = new CommonResponse();
        try {
            List<BookEntity> book = bookService.searchBooks(name,description);
            commonResponse.setStatus(STATUS_SUCCESS);
            commonResponse.setResultData(book);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(STATUS_ERROR,e.getMessage());
            return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            logger.info("end searchBook");
        }

    }





}
