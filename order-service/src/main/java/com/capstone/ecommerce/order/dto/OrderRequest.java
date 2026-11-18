package com.capstone.ecommerce.order.dto;
import jakarta.validation.constraints.*;
public record OrderRequest(@NotNull @Positive Long productId, @Positive int quantity) { }
