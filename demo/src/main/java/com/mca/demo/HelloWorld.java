package com.mca.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mca.demo.model.Book;
import com.mca.demo.services.BookService;

import jakarta.validation.Valid;

@RestController
public class HelloWorld {
    
    private final DemoApplication demoApplication;
    private final BookService bookService;
    public HelloWorld(BookService bookService, DemoApplication demoApplication){
        this.bookService=bookService;
        this.demoApplication = demoApplication;
    }

    @PostMapping("/api/v1/hello")
    public String hello(){
        return "Hello World...";
    }
    @PostMapping("/getBook")
   public Book getBook(@RequestParam("Id") Long Id){
    return bookService.getBook(Id);
    }
    @PostMapping("/saveBook/test")
    public Book saveBook(@RequestBody @Valid Book book){
        return book;
   
   }
}
