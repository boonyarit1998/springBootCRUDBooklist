package com.restapi.booklists.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book_list")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany(mappedBy = "bookList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookListItemEntity> items = new ArrayList<>();

    private String name;

    private String description;

}
