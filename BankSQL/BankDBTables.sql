create table pending_registration(
user_id serial,
first_name varchar(20) not null,
last_name varchar(20) not null,
account_type varchar(14),
account_balance decimal(2)
);

create table users(
user_id int primary key,
first_name varchar(20) not null,
last_name varchar(20) not null,
username varchar(30) not null unique,
user_password varchar(15) not null,
user_type varchar(8) not null
);

create table accounts(
account_id serial unique,
account_type varchar(14),
account_balance decimal(2),
user_id int references users(user_id)
);

create table pending_transfers(
transfer_id serial,
from_acount int references accounts(account_id),
to_account int references accounts(account_id),
transfer_amount decimal(2)
);

create table transactions(
transaction_id serial,
account_id int references accounts(account_id),
amount decimal(2)
);
