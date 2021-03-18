DELIMITER //
DROP FUNCTION IF EXISTS hello;
CREATE FUNCTION hello ()
RETURNS VARCHAR(255) DETERMINISTIC
BEGIN
 DECLARE hours INT; declare site_name VARCHAR(255);
set hours = HOUR(CURRENT_TIME());
if (hours>5 and hours <12) 
then set site_name ='Доброе утро';
elseif (hours>11 and hours <18) 
then set site_name ='Добрый день';
elseif (hours>17 and hours <24) 
then set site_name ='Добрый вечер';
else
set site_name ='Доброй ночи';
end if;
 RETURN site_name;
END//
select hello ();