package com.restapi.booklists.service;

import com.restapi.booklists.entity.BookEntity;
import com.restapi.booklists.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    public BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<BookEntity> getBook(){
        return bookRepository.findAll();
    }

    public BookEntity getBookById (Long id){
        return bookRepository.findById(id).orElse(null);
    }

    public BookEntity addBook(BookEntity bookEntity){
        return bookRepository.save(bookEntity);
    }

    public BookEntity editBook(Long id,BookEntity bookEdit){
        BookEntity bookEntity = bookRepository.findById(id).orElse(null);
        if (bookEntity != null){
            bookEntity.setName(bookEdit.getName());
            bookEntity.setDescription(bookEdit.getDescription());
            bookEntity.setCategoryId(bookEdit.getCategoryId());
            return bookRepository.save(bookEntity);
        }

        return null;
    }

    public String delete(Long id){
        BookEntity bookEntity = bookRepository.findById(id).orElse(null);
        if(bookEntity != null){
            bookRepository.deleteById(id);
            return "delete success";
        }
        return "delete fail";
    }
}
