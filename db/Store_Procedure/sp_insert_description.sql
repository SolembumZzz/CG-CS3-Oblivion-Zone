CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insert_new_description`(
    IN `brand` VARCHAR(100),
    IN `mainboard` VARCHAR(100),
    IN `CPU` VARCHAR(100),
    IN `RAM` VARCHAR(100),
    IN `VGA` VARCHAR(100),
    IN `harddrive` VARCHAR(100),
    IN `insurance` DECIMAL(12,1),
    IN `newProductId` INT
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION ROLLBACK;

    START TRANSACTION;

    INSERT INTO `description`(`brand`, `mainboard`, `CPU`, `RAM`, `VGA`, `harddrive`, `insurance`)
    VALUES (`brand`, `mainboard`, `CPU`, `RAM`, `VGA`, `harddrive`, `insurance`);

    UPDATE `products`
    SET `products`.`description` = LAST_INSERT_ID()
    WHERE `products`.`id` = `newProductId`;

    COMMIT;
END