DROP TABLE transactions;
DROP TABLE pending_transfers;
DROP TABLE accounts;
DROP TABLE users;

select user_type from users where username = 'laurendeannapena' and user_password = '2108Java';

INSERT INTO users (username, user_password, first_name, last_name, user_type)
	values('jonathanpena', '2108Java', 'Jonathan', 'Pena', 'employee');
	
select * from users;

insert into accounts (first_user, account_type, account_balance, is_approved)
	values(1, 'checking', 100, false);

update accounts set is_approved = true where first_user = 2;
update accounts set is_approved = false where account_id = 3;

truncate accounts cascade;

select * from accounts where is_approved = true and (first_user = 2 or second_user = 2);
	
