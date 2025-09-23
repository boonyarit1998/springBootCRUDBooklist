package com.restapi.booklists.service.Impl;

import com.restapi.booklists.entity.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookEntity> getAllBooks();
    Optional<BookEntity> getBookById(Long id);
    BookEntity createBook(BookEntity book);
    BookEntity updateBook(Long id,BookEntity book);
    void deleteBook(Long id);
    List<BookEntity> searchBooks(String name,String description,Integer categoryId);
}
