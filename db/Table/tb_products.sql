CREATE TABLE IF NOT EXISTS `products`
(
    `id`           INT AUTO_INCREMENT PRIMARY KEY,
    `product_name` VARCHAR(50)    NOT NULL,
    `price`        DECIMAL(12, 0) NOT NULL,
    `description`  INT DEFAULT NULL,
    `category`     VARCHAR(50),
    CONSTRAINT fk_description FOREIGN KEY (`description`) REFERENCES `description`(`id`)
)