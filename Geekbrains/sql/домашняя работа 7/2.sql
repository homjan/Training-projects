
CREATE USER user_read IDENTIFIED WITH Sha256_password BY 'Pass_9_10';
GRANT USAGE ON accounts TO user_read;
GRANT SELECT ON username TO user_read;
select user() user_read;
