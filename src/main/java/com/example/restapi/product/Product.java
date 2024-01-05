package com.example.restapi.product;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    
    @Id @GeneratedValue
    private Integer id;

    @Column
    private String description;
    @Column
    private Double price;
    @Column
    private LocalDateTime restock;
}
