package com.capstone.ecommerce.order.service;

import com.capstone.ecommerce.order.dto.OrderRequest;
import com.capstone.ecommerce.order.entity.CustomerOrder;
import com.capstone.ecommerce.order.event.OrderCreatedEvent;
import com.capstone.ecommerce.order.repository.OrderRepository;
import com.capstone.ecommerce.order.client.ProductClient;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OrderService {
    private final OrderRepository repository;
    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;
    private final ProductClient productClient;

    public OrderService(OrderRepository repository, KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate,
                        ProductClient productClient) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
        this.productClient = productClient;
    }

    public List<CustomerOrder> findAll() { return repository.findAll(); }
    public CustomerOrder findById(Long id) { return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found: " + id)); }

    public CustomerOrder create(OrderRequest request) {
        productClient.verifyProductExists(request.productId());
        CustomerOrder order = repository.save(new CustomerOrder(request.productId(), request.quantity()));
        kafkaTemplate.send("order-created", order.getId().toString(), new OrderCreatedEvent(order.getId(), order.getProductId(), order.getQuantity()));
        return order;
    }

    public CustomerOrder update(Long id, OrderRequest request) { CustomerOrder order = findById(id); order.update(request.productId(), request.quantity()); return repository.save(order); }
    public void delete(Long id) { repository.delete(findById(id)); }
}
