package com.restapi.booklists.controller;

import com.restapi.booklists.entity.BookEntity;
import com.restapi.booklists.dto.CommonResponse;
import com.restapi.booklists.dto.ErrorResponse;
import com.restapi.booklists.service.BookServiceImpl;
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

    private final BookServiceImpl bookService;

    @GetMapping()
    public ResponseEntity<Object> getAllBooks() throws Exception{
        logger.info("start getBook");
        CommonResponse commonResponse = new CommonResponse();
        try {
            List<BookEntity> book = bookService.getAllBooks();
            commonResponse.setStatus(STATUS_SUCCESS);
            commonResponse.setResultData(book);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(STATUS_ERROR,e.getMessage());
            return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            logger.info("end getBook");
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBookById(@PathVariable Long id) throws Exception{
        logger.info("start getBookById");
        CommonResponse commonResponse = new CommonResponse();
        try {
            Optional<BookEntity> book = bookService.getBookById(id);
            commonResponse.setStatus(STATUS_SUCCESS);
            commonResponse.setResultData(book);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);

        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(STATUS_ERROR,e.getMessage());
            return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            logger.info("end getBookById");
        }
    }

    @PostMapping()
    public ResponseEntity<Object> createBook(@RequestBody BookEntity bookEntity) throws Exception{
        logger.info("start addBook");
        CommonResponse commonResponse = new CommonResponse();
        try {
            BookEntity book = bookService.createBook(bookEntity);
            commonResponse.setStatus(STATUS_SUCCESS);
            commonResponse.setResultData(book);
            return new ResponseEntity<>(commonResponse, HttpStatus.CREATED);

        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(STATUS_ERROR,e.getMessage());
            return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            logger.info("end addBook");
        }


    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable Long id,@RequestBody BookEntity bookEdit) throws Exception{
        logger.info("start editBook");
        CommonResponse commonResponse = new CommonResponse();
        System.out.println(bookEdit);
        try {
            BookEntity book = bookService.updateBook(id,bookEdit);
            commonResponse.setStatus(STATUS_SUCCESS);
            commonResponse.setResultData(book);
            return new ResponseEntity<>(commonResponse, HttpStatus.CREATED);

        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(STATUS_ERROR,e.getMessage());
            return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            logger.info("end editBook");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable Long id) throws Exception{
        logger.info("start deleteBook");
        CommonResponse commonResponse = new CommonResponse();
        try {
            bookService.deleteBook(id);
            commonResponse.setStatus(STATUS_SUCCESS);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        }catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(STATUS_ERROR,e.getMessage());
            return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            logger.info("end deleteBook");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchBook(@RequestParam(required = false) String name,@RequestParam(required = false) String description ,@RequestParam(required = false) Integer categoryId) throws Exception{
        logger.info("start searchBook");
        CommonResponse commonResponse = new CommonResponse();
        try {
            List<BookEntity> book = bookService.searchBooks(name,description,categoryId);
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
