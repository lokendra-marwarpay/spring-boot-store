package com.codewithmosh.store.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.codewithmosh.store.entities.Order;
import com.codewithmosh.store.users.User;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @EntityGraph(attributePaths = "items.product")
    @Query("SELECT o FROM Order o WHERE o.customer = :customer")
    List<Order> getOrdersByCustomer(@Param("customer") User customer);
    
    @EntityGraph(attributePaths = "items.product")
    @Query("SELECT o from Order o where o.id = :orderId")
    Optional<Order> getOrderWithItems(@Param("orderId") Long orderId);
}
