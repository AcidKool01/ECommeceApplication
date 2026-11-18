package com.capstone.ecommerce.order.event;
public record OrderCreatedEvent(Long orderId, Long productId, int quantity) { }
