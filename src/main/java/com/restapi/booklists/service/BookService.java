package com.restapi.booklists.service;

import com.restapi.booklists.dto.BookResponseDTO;
import com.restapi.booklists.dto.CategoryResponseDTO;
import com.restapi.booklists.entity.BookEntity;
import com.restapi.booklists.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;


    public BookResponseDTO getBookById(Long id) {
        BookEntity book = bookRepository.findById(id).orElse(null);
        BookResponseDTO response = new BookResponseDTO(
                book.getName(),
                book.getDescription(),
                new CategoryResponseDTO(book.getCategory().getCategory(),book.getCategory().getDescription())
        );
        return response;
    }

    public List<BookEntity> getAllBooks() {
        return    bookRepository.findAll();
    }


    public BookEntity createBook(BookEntity book) {
        return bookRepository.save(book);
    }

    public BookEntity updateBook(Long id, BookEntity book) {
        BookEntity bookEntity = bookRepository.findById(id).orElse(null);
        if (bookEntity != null){
            bookEntity.setName(book.getName());
            bookEntity.setDescription(book.getDescription());
            return bookRepository.save(bookEntity);
        }

        return null;
    }

    public void deleteBook(Long id) {
        Optional<BookEntity> bookEntity = bookRepository.findById(id);
        if(bookEntity != null) {
            bookRepository.deleteById(id);
        }
    }

    public List<BookEntity> searchBooks(String name, String description) {
        return bookRepository.findBookByCriteria(name,description);
    }


}
