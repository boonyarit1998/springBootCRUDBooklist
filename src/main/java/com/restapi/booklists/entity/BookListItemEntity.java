package com.restapi.booklists.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book_list_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookListItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_list_id", nullable = false)
    private BookListEntity bookList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = true)
    private BookEntity book;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReadingStatus status;
}
