package com.restapi.booklists.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private UserEntity user ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id",nullable = false)
    private BookEntity book ;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReadingStatus status;

    private String name;

    private String description;

}
