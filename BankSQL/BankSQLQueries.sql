DROP TABLE transactions;
DROP TABLE pending_transfers;
DROP TABLE accounts;
DROP TABLE users;

select user_type from users where username = 'laurendeannapena' and user_password = '2108Java';

INSERT INTO users (username, user_password, first_name, last_name, user_type)
	values('jonathanpena', '2108Java', 'Jonathan', 'Pena', 'employee');
	
select * from users;