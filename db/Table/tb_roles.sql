CREATE TABLE IF NOT EXISTS `roles`
(
    `id`   ENUM('1', '2') PRIMARY KEY,
    `code` VARCHAR(50) NOT NULL
)