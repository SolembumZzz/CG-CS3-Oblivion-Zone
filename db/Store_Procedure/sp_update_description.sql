CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_update_description`(
    IN `descriptionId` INT,
    IN `brand` VARCHAR(100),
    IN `mainboard` VARCHAR(100),
    IN `CPU` VARCHAR(100),
    IN `RAM` VARCHAR(100),
    IN `VGA` VARCHAR(100),
    IN `harddrive` VARCHAR(100),
    IN `insurance` DECIMAL(12,1)
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION ROLLBACK;

    START TRANSACTION;

    UPDATE `description` AS d
    SET d.`brand` = `brand`,
        d.`mainboard` = `mainboard`,
        d.`CPU` = `CPU`,
        d.`RAM` = `RAM`,
        d.`VGA` = `VGA`,
        d.`harddrive` = `harddrive`,
        d.`insurance` = `insurance`
    WHERE d.`id` = `descriptionId`;

    COMMIT;
END