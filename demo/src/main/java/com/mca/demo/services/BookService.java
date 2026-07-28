package com.mca.demo.services;


import org.springframework.stereotype.Service;

import com.mca.demo.model.Book;

@Service
public class BookService {
    public Book getBook(Long id){
        Book b = new Book();
        b.setId(id);
        b.setName("springBoot");

        return b;
    }
    
        

    }
    
