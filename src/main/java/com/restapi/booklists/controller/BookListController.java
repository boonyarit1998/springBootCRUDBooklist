package com.restapi.booklists.controller;

import com.restapi.booklists.dto.CommonResponse;
import com.restapi.booklists.dto.ErrorResponse;
import com.restapi.booklists.entity.BookEntity;
import com.restapi.booklists.entity.BookListEntity;
import com.restapi.booklists.entity.ReadingStatus;
import com.restapi.booklists.entity.UserEntity;
import com.restapi.booklists.service.BookListServiceImpl;
import com.restapi.booklists.service.UserServiceImpl;
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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final BookListServiceImpl bookListService;

    private final UserServiceImpl userService;

    @GetMapping()
    public ResponseEntity<Object> getAllBookLists() throws Exception{
        logger.info("start getAllBooklistLists");
        CommonResponse commonResponse = new CommonResponse();
        try{
            List<BookListEntity> book  = bookListService.getAllBookLists();
            commonResponse.setStatus(STATUS_SUCCESS);
            commonResponse.setResultData(book);
            return new ResponseEntity<>(commonResponse, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(STATUS_ERROR, e.getMessage());
            return  new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
        logger.info("end getBook");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBooklistById(@PathVariable Long id) throws  Exception{
        logger.info("Start getbooklistById");
        CommonResponse commonResponse = new CommonResponse();
        try{
            Optional<UserEntity> user = userService.getUserById(id);
            List<BookListEntity> bookList = bookListService.getBooklistByUser(user);
            commonResponse.setStatus(STATUS_SUCCESS);
            commonResponse.setResultData(bookList);
            return new ResponseEntity<>(commonResponse,HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(STATUS_ERROR, e.getMessage());
            return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
        logger.info("end getbooklistById");
        }
    }

    @PostMapping()
    public ResponseEntity<Object> createBookList(@RequestBody BookListEntity book) throws  Exception{
        logger.info("Start createBookList");
        CommonResponse commonResponse = new CommonResponse();
        try{

            BookListEntity bookList = bookListService.createBookList(book);
            commonResponse.setStatus(STATUS_SUCCESS);
            commonResponse.setResultData(bookList);
            return new ResponseEntity<>(commonResponse,HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(STATUS_ERROR, e.getMessage());
            return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            logger.info("end createBookList");
        }
    }

    @PostMapping("/addbook")
    public ResponseEntity<Object> addBookToBookList(@RequestBody UserEntity user, @RequestBody BookEntity book , @RequestBody ReadingStatus status) throws  Exception{
        logger.info("Start addBookToBookList");
        CommonResponse commonResponse = new CommonResponse();
        try{

            BookListEntity bookList = bookListService.addBookToBooklist(user,book,status);
            commonResponse.setStatus(STATUS_SUCCESS);
            commonResponse.setResultData(bookList);
            return new ResponseEntity<>(commonResponse,HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(STATUS_ERROR, e.getMessage());
            return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            logger.info("end addBookToBookList");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateReadingStatus(@PathVariable Long id,@RequestBody ReadingStatus status) throws  Exception{
        logger.info("Start updateReadingStatus");
        CommonResponse commonResponse = new CommonResponse();
        try{

            BookListEntity bookList = bookListService.updateReadingStatus(id,status);
            commonResponse.setStatus(STATUS_SUCCESS);
            commonResponse.setResultData(bookList);
            return new ResponseEntity<>(commonResponse,HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(STATUS_ERROR, e.getMessage());
            return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            logger.info("end updateReadingStatus");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBookList(@PathVariable Long id) throws  Exception{
        logger.info("Start deleteBookList");
        CommonResponse commonResponse = new CommonResponse();
        try{

             bookListService.removeBookFromBooklist(id);
            commonResponse.setStatus(STATUS_SUCCESS);
            return new ResponseEntity<>(commonResponse,HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(STATUS_ERROR, e.getMessage());
            return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }finally {
            logger.info("end deleteBookList");
        }
    }
}
