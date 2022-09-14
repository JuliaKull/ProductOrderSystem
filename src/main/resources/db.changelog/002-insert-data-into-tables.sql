-- liquibase formatted sql
-- changeset kull:liqubase run insert data into tables

insert into customer(id,first_name, last_name, registration_code, email, telephone)
values (1,'Olga', 'Mikker', 9547854, 'mikker.o@gmail.com', 84574551255);
insert into customer(id,first_name, last_name, registration_code, email, telephone)
values (2,'Mike', 'Leons', 5487799, 'leons@gmail.com', 9112474444);
insert into customer(id,first_name, last_name, registration_code, email, telephone)
values (3,'Alex', 'Simpson', 9431574, 'a.simpson@gmail.com', 5488444445);

insert into product(name, sku_code, unit_price)
values ('IPad10', 547854, 900);
insert into product(name, sku_code, unit_price)
values ('MacBookPro', 621457, 2100);
insert into product(name, sku_code, unit_price)
values ('GalaxyA5', 667711, 540);
insert into product(name, sku_code, unit_price)
values ('IPad9', 326487, 800);
insert into product(name, sku_code, unit_price)
values ('GalaxyA7', 637951, 400);
insert into product(name, sku_code, unit_price)
values ('Ipad11', 467285, 1500);
insert into product(name, sku_code, unit_price)
values ('Nokia3310', 934571, 200);



insert into customer_order (order_number, customer_id, submission_date)
values ('1',
        (select id from customer where registration_code = 9547854),
        current_timestamp);
insert into customer_order (order_number, customer_id, submission_date)
values ('2',
        (select id from customer where registration_code = 5487799),
        current_timestamp);
insert into customer_order (order_number, customer_id, submission_date)
values ('3',
        (select id from customer where registration_code = 9431574),
        current_timestamp);

insert into orderLine(product_id, quantity, customer_order_id)
values ((select id from product where name = 'IPad10'), 100,
        (select id from customer_order where order_number = '1'));
insert into orderLine(product_id, quantity, customer_order_id)
values ((select id from product where name = 'MacBookPro'), 50,
        (select id from customer_order where order_number = '2'));
insert into orderLine(product_id, quantity, customer_order_id)
values ((select id from product where name = 'GalaxyA5'), 400,
        (select id from customer_order where order_number = '3'));
insert into orderLine(product_id, quantity, customer_order_id)
values ((select id from product where name = 'IPad9'), 70,
        (select id from customer_order where order_number = '2'));
insert into orderLine(product_id, quantity, customer_order_id)
values ((select id from product where name = 'GalaxyA7'), 700,
        (select id from customer_order where order_number = '1'));
insert into orderLine(product_id, quantity, customer_order_id)
values ((select id from product where name = 'Ipad11'), 150,
        (select id from customer_order where order_number = '3'));
insert into orderLine(product_id, quantity, customer_order_id)
values ((select id from product where name = 'Nokia3310'), 300,
        (select id from customer_order where order_number = '3'));

