package com.tanos.demo.infrastructure.mapper;

import com.tanos.demo.domain.model.Product;
import com.tanos.demo.infrastructure.drivers_adapters.ProductEntity;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {

    public static Product toProduct(ProductEntity productEntity){
        return Product.builder().id(productEntity.getId()).title(productEntity.getTitle())
                .description(productEntity.getDescription()).pubDate(productEntity.getPubDate())
                .priceProduct(productEntity.getNumPages()).build();
    }

    public static List<Product> toProduct(List<ProductEntity> productEntity){
        var result = new ArrayList<Product>();

        for (var product: productEntity) {
            result.add(Product.builder().id(product.getId()).title(product.getTitle())
                    .description(product.getDescription()).pubDate(product.getPubDate())
                    .priceProduct(product.getNumPages()).build());
        }
        return result;
    }
    public static ProductEntity toEntity(Product product){
        return ProductEntity.builder().id(product.getId()).title(product.getTitle())
                .description(product.getDescription()).pubDate(product.getPubDate())
                .numPages(product.getPriceProduct()).build();
    }

    public static List<ProductEntity> toEntity(List<Product> product){
        var result = new ArrayList<ProductEntity>();
        for (var books : product){
            result.add(ProductEntity.builder().id(books.getId()).title(books.getTitle())
                    .description(books.getDescription()).pubDate(books.getPubDate())
                    .numPages(books.getPriceProduct()).build());
        }
        return result;
    }
}
