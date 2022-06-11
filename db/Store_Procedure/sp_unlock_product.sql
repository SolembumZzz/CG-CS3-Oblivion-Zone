CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_unlock_product`(IN id INT)
BEGIN
    UPDATE `products`
    SET `products`.`locked` = 0
    WHERE `products`.`id` = id;
END