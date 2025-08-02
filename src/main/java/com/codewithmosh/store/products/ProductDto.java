package com.codewithmosh.store.products;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

// @AllArgsConstructor
// @Getter
@Data
public class ProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private Byte categoryId;
}
