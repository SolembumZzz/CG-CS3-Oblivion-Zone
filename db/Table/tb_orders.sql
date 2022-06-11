CREATE TABLE IF NOT EXISTS `orders`
(
    `id`          INT AUTO_INCREMENT PRIMARY KEY,
    `customer_id` INT NOT NULL,
    `order_date`  DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_customer_id FOREIGN KEY (`customer_id`) REFERENCES `users` (`id`)
)