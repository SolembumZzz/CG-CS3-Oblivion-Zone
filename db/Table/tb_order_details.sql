CREATE TABLE IF NOT EXISTS `order_details`
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    quantity   DECIMAL(12, 0) NOT NULL,
    order_id   INT            NOT NULL,
    product_id INT            NOT NULL,
    CONSTRAINT fk_order_id FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
    CONSTRAINT fk_product_id FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
)