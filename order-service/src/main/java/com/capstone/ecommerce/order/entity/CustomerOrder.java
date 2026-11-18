package com.capstone.ecommerce.order.entity;

import jakarta.persistence.*;
@Entity @Table(name = "orders") public class CustomerOrder {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false) private Long productId;
    @Column(nullable = false) private int quantity;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private OrderStatus status;
    protected CustomerOrder() { }
    public CustomerOrder(Long productId, int quantity) { this.productId = productId; this.quantity = quantity; this.status = OrderStatus.PENDING; }
    public Long getId() { return id; } public Long getProductId() { return productId; } public int getQuantity() { return quantity; } public OrderStatus getStatus() { return status; }
    public void update(Long productId, int quantity) { this.productId = productId; this.quantity = quantity; }
}
