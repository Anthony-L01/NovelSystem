package com.lxc.novelsystem;

import com.lxc.novelsystem.Util.JwtUtil;
import com.lxc.novelsystem.entity.book;
import com.lxc.novelsystem.service.BookService;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.print.Book;
import java.util.List;

@SpringBootTest
class NovelSystemApplicationTests {


    @Autowired
    BookService bookService;
    @Test
    void contextLoads() {
        List<book> books = bookService.fuzzySearch("Theory");
        for (book book :books
                ) {
            System.out.println(book.getId()+book.getTitle());
            
        }
    }



}
