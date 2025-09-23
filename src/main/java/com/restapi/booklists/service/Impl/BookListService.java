package com.restapi.booklists.service.Impl;

import com.restapi.booklists.entity.BookEntity;
import com.restapi.booklists.entity.BookListEntity;
import com.restapi.booklists.entity.ReadingStatus;
import com.restapi.booklists.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface BookListService {
    List<BookListEntity> getAllBookLists();
    List<BookListEntity> getBooklistByUser(Optional<UserEntity> user);
    BookListEntity createBookList(BookListEntity bookList);
    List<BookListEntity> getBooklistByUserAndStatus(UserEntity user, ReadingStatus status);
    BookListEntity addBookToBooklist(UserEntity user, BookEntity book,ReadingStatus status);
    BookListEntity updateReadingStatus(Long id,ReadingStatus status);
    void removeBookFromBooklist(Long id);
    Optional<BookListEntity> getBooklistItem(UserEntity user,BookEntity book);

}
