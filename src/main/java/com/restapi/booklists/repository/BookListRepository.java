package com.restapi.booklists.repository;

import com.restapi.booklists.entity.BookListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookListRepository extends JpaRepository<BookListEntity,Long>{
}
