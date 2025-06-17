package com.tanos.demo.infrastructure.mapper;

import com.tanos.demo.domain.model.Book;
import com.tanos.demo.infrastructure.drivers_adapters.BookEntity;

import java.util.ArrayList;
import java.util.List;

public class BookMapper {

    public static Book toBook(BookEntity bookEntity ){
        return Book.builder().id(bookEntity.getId()).title(bookEntity.getTitle())
                .description(bookEntity.getDescription()).pubDate(bookEntity.getPubDate())
                .numPages(bookEntity.getNumPages()).build();
    }

    public static List<Book> toBook(List<BookEntity> bookEntity ){
        var result = new ArrayList<Book>();

        for (var book: bookEntity) {
            result.add(Book.builder().id(book.getId()).title(book.getTitle())
                    .description(book.getDescription()).pubDate(book.getPubDate())
                    .numPages(book.getNumPages()).build());
        }
        return result;
    }
    public static BookEntity toEntity(Book book ){
        return BookEntity.builder().id(book.getId()).title(book.getTitle())
                .description(book.getDescription()).pubDate(book.getPubDate())
                .numPages(book.getNumPages()).build();
    }

    public static List<BookEntity> toEntity(List<Book> book ){
        var result = new ArrayList<BookEntity>();
        for (var books : book){
            result.add(BookEntity.builder().id(books.getId()).title(books.getTitle())
                    .description(books.getDescription()).pubDate(books.getPubDate())
                    .numPages(books.getNumPages()).build());
        }
        return result;
    }
}
