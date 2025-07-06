package com.tanos.demo.domain.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private int priceProduct;
    private LocalDate pubDate;
    private String description;

    @CreationTimestamp
    private LocalDate createDate;

    @UpdateTimestamp
    private LocalDate updateDate;
}
