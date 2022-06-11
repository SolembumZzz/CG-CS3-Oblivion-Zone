CREATE
    DEFINER = `root`@`localhost` PROCEDURE `sp_check_product_if_locked`(IN id INT, OUT isLocked BOOLEAN)
BEGIN
    IF ((SELECT p.`locked` FROM `products` AS p WHERE p.id = id) = 0)
    THEN
        SET isLocked = FALSE;
    ELSE
        SET isLocked = TRUE;
    END IF;
END