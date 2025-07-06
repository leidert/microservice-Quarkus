package com.tanos.demo.infrastructura.drivers_adapters;

import com.tanos.demo.domain.model.Product;

import java.time.LocalDate;
import java.util.List;

public class BookMother {

    public static Product getBook(){
        return Product.builder()
                .id(1L)
                .title("don quijote")
                .pubDate(LocalDate.parse("2025-06-11"))
                .description("test don quijote")
                .priceProduct(200).build();
    }

    public static Product insertBook(){
        return Product.builder()
                .title("don quijote")
                .pubDate(LocalDate.parse("2025-06-11"))
                .description("test don quijote")
                .priceProduct(200).build();
    }

    public static List<Product> gatAllBooks(){
        return List.of(getBook());
    }
}
