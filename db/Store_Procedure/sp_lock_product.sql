CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_lock_product`(
	IN id INT
)
BEGIN
UPDATE `products`
SET `products`.`locked` = 1
WHERE `products`.`id` = id;
END