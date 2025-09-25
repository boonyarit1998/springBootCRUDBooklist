package com.restapi.booklists.repository;

import com.restapi.booklists.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity,Long> {

    @Query("SELECT b FROM BookEntity b WHERE"+
            "(:name IS NULL OR LOWER(b.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:description IS NULL OR LOWER(b.description) LIKE LOWER(CONCAT('%', :description, '%'))) "  )
    List<BookEntity> findBookByCriteria(@Param("name") String name,
                                        @Param("description") String description);
}
