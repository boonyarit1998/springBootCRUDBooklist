package com.restapi.booklists.contrller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/book")
public class BookController {

    @GetMapping()
    public String getBook(){
        return "get book";
    }

    @GetMapping("/{id}")
    public String getBookById(@PathVariable Long id){
        return "get book by id"+id;
    }

    @PostMapping()
    public String addBook(){
        return "add book";
    }

    @PutMapping("/{id}")
    public String editBook(@PathVariable Long id){
        return "edit book"+id;
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id){
        return "delete book "+id;
    }



}
