create table users(
username varchar(30) not null unique,
user_password varchar(15) not null,
first_name varchar(20) not null,
last_name varchar(20) not null,
user_type varchar(8) not null
);

create table accounts(
account_id serial unique,
username varchar(30) references users(username),
username2 varchar(30) references users(username),
account_type varchar(14),
account_balance decimal(2),
is_approved boolean
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
username varchar(30) references users(username),
amount decimal(2)
);
