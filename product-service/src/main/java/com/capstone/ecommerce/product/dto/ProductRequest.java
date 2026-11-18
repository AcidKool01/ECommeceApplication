package com.capstone.ecommerce.product.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProductRequest(@NotBlank @Size(max = 120) String name, @NotNull @DecimalMin("0.01") BigDecimal price, @Size(max = 500) String description) { }
