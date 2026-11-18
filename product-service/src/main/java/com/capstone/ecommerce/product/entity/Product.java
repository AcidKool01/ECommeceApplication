package com.capstone.ecommerce.product.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 120) private String name;
    @Column(nullable = false, precision = 12, scale = 2) private BigDecimal price;
    @Column(length = 500) private String description;

    protected Product() { }
    public Product(String name, BigDecimal price, String description) { this.name = name; this.price = price; this.description = description; }
    public Long getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
    public String getDescription() { return description; }
    public void update(String name, BigDecimal price, String description) { this.name = name; this.price = price; this.description = description; }
}
