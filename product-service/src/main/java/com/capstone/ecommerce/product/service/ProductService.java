package com.capstone.ecommerce.product.service;

import com.capstone.ecommerce.product.dto.ProductRequest;
import com.capstone.ecommerce.product.entity.Product;
import com.capstone.ecommerce.product.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;
    public ProductService(ProductRepository repository) { this.repository = repository; }
    public List<Product> findAll() { return repository.findAll(); }
    public Product findById(Long id) { return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found: " + id)); }
    public Product create(ProductRequest request) { return repository.save(new Product(request.name(), request.price(), request.description())); }
    public Product update(Long id, ProductRequest request) { Product product = findById(id); product.update(request.name(), request.price(), request.description()); return repository.save(product); }
    public void delete(Long id) { repository.delete(findById(id)); }
}
