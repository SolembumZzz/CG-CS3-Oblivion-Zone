CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insert_new_product`(
    IN `name` VARCHAR(50),
    IN `price` DECIMAL(12, 3),
    IN `quantity` INT,
    IN `category` VARCHAR(50),
    OUT `isInserted` BOOLEAN,
    OUT `newProductId` INT
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION ROLLBACK;
    SET `isInserted` = FALSE;

    START TRANSACTION;

    INSERT INTO `products`(`product_name`, `price`, `quantity`, `category`)
    VALUES (`name`, `price`, `quantity`, `category`);

    COMMIT;

    SET `newProductId` = LAST_INSERT_ID();
    SET `isInserted` = TRUE;
END