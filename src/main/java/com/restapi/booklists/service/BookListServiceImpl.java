package com.restapi.booklists.service;

import com.restapi.booklists.entity.BookEntity;
import com.restapi.booklists.entity.BookListEntity;
import com.restapi.booklists.entity.ReadingStatus;
import com.restapi.booklists.entity.UserEntity;
import com.restapi.booklists.repository.BookListRepository;
import com.restapi.booklists.repository.BookRepository;
import com.restapi.booklists.service.Impl.BookListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookListServiceImpl implements BookListService {

    private final BookListRepository bookListRepository;

    @Override
    public List<BookListEntity> getAllBookLists() {
        return bookListRepository.findAll();
    }

    @Override
    public List<BookListEntity> getBooklistByUser(Optional<UserEntity> user) {
        return bookListRepository.findByUser(user);
    }

    @Override
    public BookListEntity createBookList(BookListEntity bookList) {
        return bookListRepository.save(bookList);
    }

    @Override
    public List<BookListEntity> getBooklistByUserAndStatus(UserEntity user, ReadingStatus status) {
        return bookListRepository.findByUserAndStatus(user,status);
    }



    @Override
    public BookListEntity addBookToBooklist(UserEntity user, BookEntity book, ReadingStatus status) {
        BookListEntity bookList = new BookListEntity();
        bookList.setUser(user);
        bookList.setBook(book);
        bookList.setStatus(status);
        return bookListRepository.save(bookList);
    }

    @Override
    public BookListEntity updateReadingStatus(Long id, ReadingStatus status) {
        BookListEntity bookList = bookListRepository.findById(id).orElse(null);
        if(bookList != null){
            bookList.setStatus(status);
            return bookListRepository.save(bookList);
        }
        return  null;

    }

    @Override
    public void removeBookFromBooklist(Long id) {
        bookListRepository.deleteById(id);

    }

    @Override
    public Optional<BookListEntity> getBooklistItem(UserEntity user, BookEntity book) {
        return bookListRepository.findByUserAndBook(user,book);
    }
}
