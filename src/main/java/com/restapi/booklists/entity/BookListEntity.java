package com.restapi.booklists.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private String name;

    @NotNull
    private String description;

}
