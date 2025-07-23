package com.codewithmosh.store.entities;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "carts")
public class Cart {
    @Id
    // @ColumnDefault("(uuid_to_bin(uuid()))")
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id"
    // , columnDefinition = "BINARY(16)" // already converted uuid to bin in migration on db level so no needed
    )
    private UUID id;

    @Column(name = "date_created", insertable = false, updatable = false)
    private LocalDate dateCreated;

    @OneToMany(mappedBy = "cart")
    private Set<CartItem> cartItems = new LinkedHashSet<>();
}
