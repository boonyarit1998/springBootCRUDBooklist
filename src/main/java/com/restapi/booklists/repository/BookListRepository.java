package com.restapi.booklists.repository;

import com.restapi.booklists.entity.BookEntity;
import com.restapi.booklists.entity.BookListEntity;
import com.restapi.booklists.entity.ReadingStatus;
import com.restapi.booklists.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookListRepository extends JpaRepository<BookListEntity,Long>{

    List<BookListRepository> findByUser (UserEntity user);

    List<BookListRepository> findByStatus (ReadingStatus status);

    Optional<BookListRepository> findByUserAndBook(UserEntity user, BookEntity book);

}
