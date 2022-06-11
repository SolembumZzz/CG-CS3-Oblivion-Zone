CREATE PROCEDURE `sp_update_product`(
    IN `productId` INT,
    IN `name` VARCHAR(50),
    IN `price` DECIMAL(12, 3),
    IN `quantity` INT,
    IN `category` VARCHAR(50),
    OUT `isEdited` BOOLEAN
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION ROLLBACK;
    SET `isEdited` = FALSE;

    START TRANSACTION;

    UPDATE `products` AS p
    SET p.`product_name` = `name`,
        p.`price`        = `price`,
        p.`quantity`     = `quantity`,
        p.`category`     = `category`
    WHERE p.`id` = `productId`;
    COMMIT;

    SET `isEdited` = TRUE;
END
