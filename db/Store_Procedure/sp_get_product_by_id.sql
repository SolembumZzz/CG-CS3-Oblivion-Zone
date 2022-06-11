CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_select_product_by_id`(
    IN id INT
)
BEGIN
    SELECT *
    FROM `products`
    WHERE `products`.`id` = id;
END