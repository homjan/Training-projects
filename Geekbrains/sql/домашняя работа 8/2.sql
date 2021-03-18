DELIMITER //

DROP TRIGGER IF EXISTS products_null;
CREATE TRIGGER products_null before INSERT ON products
FOR EACH ROW
BEGIN
 DECLARE cat_id INT;
 set cat_id =0;
 if ( (SELECT count(id) from products where new.name is null)>0 and 
 (SELECT count(id) from products where new.desription is null)>0 ) 
then set cat_id =1;
end if;
IF ( cat_id>0 ) THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "Error 45000";
end if;
END//

INSERT INTO products VALUES (8, null, null, 100, null, null, null);

select *
from products;

