package com.restapi.booklists.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "book")
@Entity
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;

    public String description;

    public int categoryId;
}
