CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP not NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    CONSTRAINT orders_users_id_fk Foreign Key (customer_id) REFERENCES users (id)
);

CREATE TABLE order_items (
    id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL,
    quantity INT NOT NULL,
    CONSTRAINT order_items_order_id_fk Foreign Key (order_id) REFERENCES orders (id),
    CONSTRAINT order_items_product_id_fk Foreign Key (product_id) REFERENCES products (id)
);