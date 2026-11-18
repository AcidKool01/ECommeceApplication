package com.capstone.ecommerce.inventory.event;

public record OrderCreatedEvent(Long orderId, Long productId, int quantity) { }
