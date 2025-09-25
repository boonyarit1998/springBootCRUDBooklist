package com.restapi.booklists.service;

import com.restapi.booklists.entity.BookEntity;
import com.restapi.booklists.entity.BookListEntity;
import com.restapi.booklists.entity.ReadingStatus;
import com.restapi.booklists.entity.UserEntity;
import com.restapi.booklists.repository.BookListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookListService {

    private final BookListRepository bookListRepository;

    public List<BookListEntity> getAllBookLists() {
        return bookListRepository.findAll();
    }

    public List<BookListEntity> getBooklistByUser(Optional<UserEntity> user) {
        return bookListRepository.findByUser(user);
    }

    public BookListEntity createBookList(BookListEntity bookList) {
        return bookListRepository.save(bookList);
    }

    public List<BookListEntity> getBooklistByUserAndStatus(UserEntity user, ReadingStatus status) {
        return bookListRepository.findByUserAndStatus(user,status);
    }



    public BookListEntity addBookToBooklist(UserEntity user, BookEntity book, ReadingStatus status) {
        BookListEntity bookList = new BookListEntity();
        bookList.setUser(user);
        bookList.setBook(book);
        bookList.setStatus(status);
        return bookListRepository.save(bookList);
    }

    public BookListEntity updateReadingStatus(Long id, ReadingStatus status) {
        BookListEntity bookList = bookListRepository.findById(id).orElse(null);
        if(bookList != null){
            bookList.setStatus(status);
            return bookListRepository.save(bookList);
        }
        return  null;

    }

    public void removeBookFromBooklist(Long id) {
        bookListRepository.deleteById(id);

    }

    public Optional<BookListEntity> getBooklistItem(UserEntity user, BookEntity book) {
        return bookListRepository.findByUserAndBook(user,book);
    }
}
