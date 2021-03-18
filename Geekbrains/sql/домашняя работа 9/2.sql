
DELIMITER //
DROP PROCEDURE IF EXISTS add_million;
CREATE PROCEDURE add_million ()
BEGIN
 DECLARE n_1 INT; 
set n_1 = 0;
WHILE n_1 < 1000000 DO
INSERT INTO users VALUES (n_1, null, null, null, null);
SET n_1 = n_1 + 1;
 END WHILE;
END//
call add_million ()


/*
delete from users where id>=0;
select *
from users;
*/
