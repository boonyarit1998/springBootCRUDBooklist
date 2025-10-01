package com.restapi.booklists.service;

import com.restapi.booklists.dto.BookRequestDTO;
import com.restapi.booklists.dto.BookResponseDTO;
import com.restapi.booklists.entity.BookEntity;
import com.restapi.booklists.entity.CategoryEntity;
import com.restapi.booklists.exception.ResourceNotFoundException;
import com.restapi.booklists.repository.BookRepository;
import com.restapi.booklists.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final CategoryRepository categoryRepository;

    public List<BookResponseDTO> getAllBooks() {
        List<BookResponseDTO> response = bookRepository.findAll().stream().map(BookResponseDTO::toDTO).toList();
        return  response  ;
    }

    public BookResponseDTO getBookById(Long id) {
        BookEntity book = bookRepository.findById(id).orElse(null);
        return BookResponseDTO.toDTO(book);
    }


    public BookResponseDTO createBook(BookRequestDTO book) {
        CategoryEntity category = categoryRepository.findById(book.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + book.getCategoryId()));

        BookEntity newBook = new BookEntity();
        newBook.setName(book.getName());
        newBook.setDescription(book.getDescription());
        newBook.setCategory(category);
        return BookResponseDTO.toDTO(bookRepository.save(newBook));
    }

    public BookResponseDTO updateBook(Long id, BookRequestDTO book) {
        BookEntity bookEntity = bookRepository.findById(id).orElse(null);
        if (bookEntity != null){
            bookEntity.setName(book.getName());
            bookEntity.setDescription(book.getDescription());
            CategoryEntity category = categoryRepository.findById(book.getCategoryId()).orElse(null);
            bookEntity.setCategory(category);
            return BookResponseDTO.toDTO(bookRepository.save(bookEntity));
        }

        return null;
    }

    public void deleteBook(Long id) {
        BookEntity bookEntity = bookRepository.findById(id).orElse(null);
        if(bookEntity != null) {
            bookRepository.deleteById(id);
        }
    }

    public List<BookEntity> searchBooks(String name, String description) {
        return bookRepository.findBookByCriteria(name,description);
    }


}
