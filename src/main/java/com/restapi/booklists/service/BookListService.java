package com.restapi.booklists.service;

import com.restapi.booklists.dto.BookListRequestDTO;
import com.restapi.booklists.dto.BookListResponseDTO;
import com.restapi.booklists.dto.BookRequestDTO;
import com.restapi.booklists.dto.BookResponseDTO;
import com.restapi.booklists.entity.*;
import com.restapi.booklists.repository.BookListRepository;
import com.restapi.booklists.repository.BookRepository;
import com.restapi.booklists.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookListService {

    private final BookListRepository bookListRepository;

    private final BookRepository bookRepository;

    private final CategoryRepository categoryRepository;

    public List<BookListResponseDTO> getAllBookLists() {
        List<BookListResponseDTO> response = bookListRepository.findAll().stream().map(BookListResponseDTO::toDTO).toList();
        return response;
    }

    public BookListResponseDTO getBookListById(Long id){
        return BookListResponseDTO.toDTO(bookListRepository.findById(id).orElse(null));
    }


    public BookListResponseDTO createBookList(BookListRequestDTO bookList) {
        BookListEntity newList = new BookListEntity();
        newList.setName(bookList.getName());
        newList.setDescription(bookList.getDescription());
        return BookListResponseDTO.toDTO(bookListRepository.save(newList));
    }




    public BookListResponseDTO addBookToBooklist(Long bookListId, Long bookId,ReadingStatus status) {

        BookEntity book = bookRepository.findById(bookId).orElse(null);
        BookListEntity bookList = bookListRepository.findById(bookListId).orElse(null);
        if(bookList != null) {
            BookListItemEntity item = new BookListItemEntity();
            item.setBookList(bookList);
            item.setBook(book);
            item.setStatus(status);
            bookList.getItems().add(item);
            return BookListResponseDTO.toDTO(bookListRepository.save(bookList));
        }
        return null;
    }

    public BookListResponseDTO updateBooklist(Long id,BookListRequestDTO request){
        BookListEntity bookList = bookListRepository.findById(id).orElse(null);
        bookList.setName(request.getName());
        bookList.setDescription(request.getDescription());
        return BookListResponseDTO.toDTO(bookListRepository.save(bookList));
    }

    public void deleteBookList(Long id) {
        BookListEntity bookList = bookListRepository.findById(id).orElse(null);
        if(bookList != null) {
            bookListRepository.deleteById(id);
        }
    }



}
