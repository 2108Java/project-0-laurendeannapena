create table users(
user_id serial PRIMARY KEY,
username varchar(30) not null unique,
user_password varchar(15) not null,
first_name varchar(20) not null,
last_name varchar(20) not null,
user_type varchar(8) not null
);

create table accounts(
account_id serial unique,
first_user int REFERENCES users(user_id),
second_user int REFERENCES users(user_id),
account_type varchar(14),
account_balance decimal,
is_approved boolean
);

create table pending_transfers(
transfer_id serial,
from_account int references accounts(account_id),
to_account int references accounts(account_id),
transfer_amount decimal(2)
);
