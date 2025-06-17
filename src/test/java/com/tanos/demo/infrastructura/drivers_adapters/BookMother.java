package com.tanos.demo.infrastructura.drivers_adapters;

import com.tanos.demo.domain.model.Book;

import java.time.LocalDate;
import java.util.List;

public class BookMother {

    public static Book getBook(){
        return Book.builder()
                .id(1L)
                .title("don quijote")
                .pubDate(LocalDate.parse("2025-06-11"))
                .description("test don quijote")
                .numPages(200).build();
    }

    public static List<Book> gatAllBooks(){
        return List.of(getBook());
    }
}
