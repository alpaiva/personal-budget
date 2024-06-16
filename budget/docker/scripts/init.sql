create database financeiro;

\c financeiro;

create table users (
    id serial PRIMARY KEY,
    name varchar(250) not null,
    email varchar(250) not null,
    pwd varchar(250) not null
);

create table transactions (
    id serial PRIMARY KEY,
    user_id integer,
    transaction_date timestamp not null default current_timestamp,
    amount real not null,
    type varchar(1) not null,
    memo varchar(250),
    CONSTRAINT fk_user
      FOREIGN KEY(user_id)
        REFERENCES users(id)
);