package com.capstone.ecommerce.inventory.service;

import com.capstone.ecommerce.inventory.dto.InventoryRequest;
import com.capstone.ecommerce.inventory.entity.Inventory;
import com.capstone.ecommerce.inventory.event.OrderCreatedEvent;
import com.capstone.ecommerce.inventory.repository.InventoryRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class InventoryService {
    private final InventoryRepository repository;

    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    public List<Inventory> findAll() { return repository.findAll(); }

    public Inventory findByProductId(Long productId) {
        return repository.findByProductId(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory not found for product: " + productId));
    }

    public Inventory create(InventoryRequest request) {
        return repository.save(new Inventory(request.productId(), request.availableQuantity()));
    }

    public Inventory update(Long productId, InventoryRequest request) {
        Inventory item = findByProductId(productId);
        item.setAvailableQuantity(request.availableQuantity());
        return repository.save(item);
    }

    public void delete(Long productId) { repository.delete(findByProductId(productId)); }

    @Transactional
    public void reserve(OrderCreatedEvent event) {
        repository.findByProductId(event.productId()).ifPresent(item -> {
            if (!item.reserve(event.quantity())) {
                throw new IllegalStateException("Insufficient stock for product " + event.productId());
            }
        });
    }
}
