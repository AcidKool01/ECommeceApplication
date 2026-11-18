package com.capstone.ecommerce.inventory.controller;

import com.capstone.ecommerce.inventory.dto.InventoryRequest;
import com.capstone.ecommerce.inventory.entity.Inventory;
import com.capstone.ecommerce.inventory.service.InventoryService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public List<Inventory> findAll() { return inventoryService.findAll(); }

    @GetMapping("/{productId}")
    public Inventory find(@PathVariable Long productId) { return inventoryService.findByProductId(productId); }

    @PostMapping
    public ResponseEntity<Inventory> create(@Valid @RequestBody InventoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.create(request));
    }

    @PutMapping("/{productId}")
    public Inventory update(@PathVariable Long productId, @Valid @RequestBody InventoryRequest request) {
        return inventoryService.update(productId, request);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long productId) { inventoryService.delete(productId); }
}
