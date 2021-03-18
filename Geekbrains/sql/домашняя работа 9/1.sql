DELIMITER //
DROP TRIGGER IF EXISTS logs_users;
CREATE TRIGGER logs_users
 before INSERT ON users
FOR EACH ROW
BEGIN
 insert into logs VALUES (NOW(), 'users', new.id, new.name);
END//
DROP TRIGGER IF EXISTS logs_catalogs;
CREATE TRIGGER logs_catalogs
 before INSERT ON catalogs
FOR EACH ROW
BEGIN
 insert into logs VALUES (NOW(), 'catalogs', new.id, new.name);
END//
DROP TRIGGER IF EXISTS logs_products;
CREATE TRIGGER logs_products 
 before INSERT ON products 
FOR EACH ROW
BEGIN
 insert into logs VALUES (NOW(), 'products', new.id, new.name);
END//
INSERT INTO users VALUES (1, 'Иван', null, null, null);
INSERT INTO catalogs VALUES (7, 'Мыши');
INSERT INTO products VALUES (4, 'Жесткий диск', null, 200, null, null, null);


select *
from logs;

