package com.capstone.ecommerce.product.controller;

import com.capstone.ecommerce.product.dto.ProductRequest;
import com.capstone.ecommerce.product.entity.Product;
import com.capstone.ecommerce.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;
    public ProductController(ProductService service) { this.service = service; }
    @GetMapping public List<Product> findAll() { return service.findAll(); }
    @GetMapping("/{id}") public Product findById(@PathVariable Long id) { return service.findById(id); }
    @PostMapping public ResponseEntity<Product> create(@Valid @RequestBody ProductRequest request) { return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request)); }
    @PutMapping("/{id}") public Product update(@PathVariable Long id, @Valid @RequestBody ProductRequest request) { return service.update(id, request); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id) { service.delete(id); }
}
