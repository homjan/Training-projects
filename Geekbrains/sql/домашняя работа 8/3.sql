DELIMITER //
DROP FUNCTION IF EXISTS fibonaschi;
CREATE FUNCTION fibonaschi (number_f int)
RETURNS INT DETERMINISTIC
BEGIN
 DECLARE n_1 INT; declare n_2 INT; declare n_sum INT;
set n_1 = 0;
set n_2 = 1;
set n_sum = 0;
WHILE number_f > 0 DO
set	n_sum = n_1 + n_2;
set n_1=n_2;
set n_2 = n_sum;
SET number_f = number_f - 1;
  END WHILE;
 RETURN n_1;
END//
select fibonaschi (10);