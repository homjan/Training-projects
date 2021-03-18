use shop;

DROP USER shop_read;
CREATE USER shop_read IDENTIFIED WITH Sha256_password BY 'Pass_1234';
GRANT SELECT ON * TO shop_read; // Выбираем все таблицы базы данных shop 
select user() shop_read;



DROP USER shop1;
CREATE USER shop1 IDENTIFIED WITH Sha256_password BY 'Pass_5678';
GRANT select ON * TO shop1;
select user() shop1;
select * from catalogs;
insert into catalogs values(7, 'РљР»Р°РІРёР°С‚СѓСЂС‹');

