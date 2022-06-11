CREATE
    ALGORITHM = UNDEFINED
    DEFINER = `root`@`localhost`
    SQL SECURITY DEFINER
    VIEW `vw_all_products_with_description` AS
SELECT
    `p`.`id` AS `id`,
    `p`.`product_name` AS `product_name`,
    `p`.`price` AS `price`,
    `p`.`category` AS `category`,
    `d`.`brand` AS `brand`,
    `d`.`mainboard` AS `mainboard`,
    `d`.`CPU` AS `CPU`,
    `d`.`RAM` AS `RAM`,
    `d`.`VGA` AS `VGA`,
    `d`.`harddrive` AS `harddrive`,
    `d`.`insurance` AS `insurance`
FROM
    (`products` `p`
        JOIN `description` `d` ON (((`p`.`description` = `d`.`id`)
        AND (`p`.`locked` = 0))))