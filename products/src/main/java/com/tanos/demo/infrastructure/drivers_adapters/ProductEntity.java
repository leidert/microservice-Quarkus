package com.tanos.demo.infrastructure.drivers_adapters;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BookEntity")
public class ProductEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private int numPages;
    private LocalDate pubDate;
    private String description;

    @CreationTimestamp
    private LocalDate createDate;

    @UpdateTimestamp
    private LocalDate updateDate;
}
