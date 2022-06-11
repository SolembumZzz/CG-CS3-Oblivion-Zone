CREATE TABLE IF NOT EXISTS `customers`(
      `id` INT AUTO_INCREMENT PRIMARY KEY,
      `last_name` VARCHAR(50) NOT NULL,
      `first_name` VARCHAR(50) NOT NULL,
      `email` VARCHAR(50) NULL DEFAULT NULL,
      `phone` VARCHAR(50) NULL DEFAULT NULL,
      `address` VARCHAR(50) NULL DEFAULT NULL,
      `role` ENUM('1', '2') NOT NULL DEFAULT 2,
      CONSTRAINT fk_role FOREIGN KEY (`role`) REFERENCES `roles`(`id`)
)