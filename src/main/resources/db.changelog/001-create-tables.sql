-- liquibase formatted sql
-- changeset kull:liqubase run create tables
create table if not exists customer
(
    id               serial primary key,
    first_name        varchar(20)        not null,
    last_name         varchar(20)        not null,
    registration_code bigint unique      not null,
    email            varchar(50) unique not null,
    telephone        varchar(50)        not null
    );

create table if not exists customer_order
(
    id              serial primary key,
    order_number    varchar(20) unique not null,
    customer_id     int                not null,
    constraint fk_customer
    foreign key (customer_id)
    references customer (id),
    submission_date timestamp          not null
    );

create table if not exists product
(
    id        serial primary key,
    name      varchar(100) unique not null,
    sku_code   int unique          not null,
    unit_price int                 not null
    );

create table if not exists order_line
(
    id                serial primary key,
    product_id        int not null,
    quantity          int not null,
    customer_order_id int not null,
    constraint fk_product
    foreign key (product_id)
    references product (id),
    constraint fk_customer_order
    foreign key (customer_order_id)
    references customer_order (id)
    );