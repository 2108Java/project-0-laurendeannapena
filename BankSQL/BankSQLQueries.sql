DROP TABLE transactions;
DROP TABLE pending_transfers;
DROP TABLE accounts;
DROP TABLE users;

select user_type from users where username = 'laurendeannapena' and user_password = '2108Java';