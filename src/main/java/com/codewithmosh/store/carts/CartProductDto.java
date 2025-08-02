package com.codewithmosh.store.carts;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CartProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
}
