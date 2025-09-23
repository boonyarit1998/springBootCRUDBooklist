package com.restapi.booklists.service;

import com.restapi.booklists.entity.BookEntity;
import com.restapi.booklists.repository.BookRepository;
import com.restapi.booklists.service.Impl.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;


    @Override
    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<BookEntity> getBookById(Long id) {
        return bookRepository.findById(id);
    }


    @Override
    public BookEntity createBook(BookEntity book) {
        return bookRepository.save(book);
    }

    @Override
    public BookEntity updateBook(Long id, BookEntity book) {
        BookEntity bookEntity = bookRepository.findById(id).orElse(null);
        if (bookEntity != null){
            bookEntity.setName(book.getName());
            bookEntity.setDescription(book.getDescription());
            bookEntity.setCategoryId(book.getCategoryId());
            return bookRepository.save(bookEntity);
        }

        return null;
    }

    @Override
    public void deleteBook(Long id) {
        Optional<BookEntity> bookEntity = bookRepository.findById(id);
        if(bookEntity != null) {
            bookRepository.deleteById(id);
        }
    }

    @Override
    public List<BookEntity> searchBooks(String name, String description, Integer categoryId) {
        return bookRepository.findBookByCriteria(name,description,categoryId);
    }


}
