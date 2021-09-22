--dummy data and saved sample queries
INSERT INTO users (username, user_password, first_name, last_name, user_type)
	values('laurenpena', '2108Java', 'Lauren', 'Pena', 'customer');

INSERT INTO users (username, user_password, first_name, last_name, user_type)
	values('customer', 'customer', 'Lauren', 'Pena', 'customer');

INSERT INTO users (username, user_password, first_name, last_name, user_type)
	values('employee', 'employee', 'Lauren', 'Pena', 'employee');
	
select * from users;

insert into accounts (first_user, account_type, account_balance, is_approved)
	values('4', 'checking', 100, true);

insert into accounts (first_user, account_type, account_balance, is_approved)
	values('4', 'savings', 100, true);

update accounts set is_approved = false where account_id = 11;

delete from accounts where account_id = 12;

select * from accounts;

select * from pending_transfers;
