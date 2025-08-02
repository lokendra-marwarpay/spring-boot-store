package com.codewithmosh.store.carts;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddItemsToCartRequest {
    @NotNull
    private Long productId;    
}
