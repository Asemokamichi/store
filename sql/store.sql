drop table if exists product_characteristic;

drop table if exists characteristics;

drop table if exists carts;

drop table if exists orders;

drop table if exists purchases;

drop table if exists reviews;

drop table if exists products;

drop table if exists categories;

drop table if exists users;

create table categories
(
    category_id   serial8 primary key,
    category_name varchar(50) not null
);


create table products
(
    product_id   serial8 primary key,
    category_id  int8        references categories (category_id) on delete set null,
    product_name varchar(50) not null,
    cost         int8        not null
);

create table characteristics
(
    characteristic_id   serial8 primary key,
    category_id         int8        references categories (category_id) on delete set null,
    characteristic_name varchar(50) not null,
    filter              bool default false
);

create table product_characteristic
(
    product_characteristic_id serial primary key,
    product_id                int8 references products (product_id) on delete cascade,
    characteristic_id         int8         references characteristics (characteristic_id) on delete set null,
    characteristic            varchar(120) not null,
    unique (product_id, characteristic_id)
);

create table users
(
    user_id      serial8 primary key,
    role         varchar not null,
    email        varchar not null,
    password     varchar not null,
    user_name    varchar not null,
    user_surname varchar not null
);

create table carts
(
    cart_id    serial8 primary key,
    user_id    int8 references users (user_id) on delete cascade,
    product_id int8 references products (product_id) on delete cascade,
    count      int default 1
);


create table purchases
(
    purchase_id serial8 primary key,
    user_id     int8 references users (user_id),
    status      int2 not null default 0,
    date_beg    date          default now(),
    address     text
);



create table orders
(
    order_id    serial8 primary key,
    product_id  int8 references products (product_id) on delete cascade,
    purchase_id int8 references purchases (purchase_id) on delete cascade,
    count       int default 1
);


create table reviews
(
    review_id  serial8 primary key,
    user_id    int8 references users (user_id) on delete cascade,
    product_id int8 references products (product_id) on delete cascade,
    score      int2 default 5,
    review     text,
    access     bool default true
);

insert into users (role, email, password, user_name, user_surname)
values (0, 'user1@gmail.com', '$2a$12$Nq642BLCcFBBufXznaSFdupbPJxcCM0vVll/AaBoXsQNLvyYjNPUW', 'John', 'Doe'),
       (1, 'user2@gmail.com', '$2a$12$zxFURej/TdRddWRVJ8IVZuY0pyhKhIvBTmb.7e7E46MRp9ORhMBtq', 'Jane', 'Smith'),
       (0, 'user3@gmail.com', '$2a$12$7dJIIsQmdfdiuv3CfsXrxuSJJBmOhWkorUbIqIqYiWybXczsoMPza', 'Alice', 'Johnson');

insert into categories (category_name)
values ('Электроника'),
       ('Одежда'),
       ('Книги');

insert into characteristics (category_id, characteristic_name)
values (1, 'Производитель'),
       (1, 'Операционная система'),
       (1, 'Модельный год'),
       (1, 'Диагональ дисплея, дюйм'),
       (2, 'Размер'),
       (2, 'Цвет'),
       (2, 'Бренд'),
       (3, 'Автор'),
       (3, 'Издатель'),
       (3, 'Год выпуска');

insert into products (category_id, product_name, cost)
values (1, 'Смартфон Samsung Galaxy S21', 100000),
       (1, 'Ноутбук Dell XPS 15', 150000),
       (1, 'Планшет Apple iPad Pro', 80000),
       (2, 'Футболка Nike', 3000),
       (2, 'Джинсы Levi''s 501', 5000),
       (3, 'Мастер и Маргарита', 1500),
       (3, 'Война и мир', 2000);


-- Добавление данных в таблицу products для категории 'Электроника'
INSERT INTO products (category_id, product_name, cost)
VALUES (1, 'Смартфон Samsung Galaxy S21', 399990),
       (1, 'Ноутбук Apple MacBook Pro', 1329990),
       (1, 'Телевизор LG OLED65C1', 1419990);

-- Привязка характеристик к продуктам для категории 'Электроника'
INSERT INTO product_characteristic (product_id, characteristic_id, characteristic)
VALUES (1, 1, 'Samsung'),
       (1, 2, 'Android'),
       (1, 3, '2021'),
       (1, 4, '6.2'),
       (2, 1, 'Apple'),
       (2, 2, 'macOS'),
       (2, 3, '2020'),
       (2, 4, '-'), -- Диагональ дисплея для ноутбука не применима
       (3, 1, 'LG'),
       (3, 2, 'webOS'),
       (3, 3, '2021'),
       (3, 4, '65');

-- Добавление данных в таблицу products для категории 'Одежда'
INSERT INTO products (category_id, product_name, cost)
VALUES (2, 'Футболка Nike', 15990),
       (2, 'Джинсы Wrangler', 39590),
       (2, 'Куртка Columbia', 89590);

-- Привязка характеристик к продуктам для категории 'Одежда'
INSERT INTO product_characteristic (product_id, characteristic_id, characteristic)
VALUES (4, 5, 'M'),
       (4, 6, 'Синий'),
       (4, 7, 'Nike'),
       (5, 5, 'L'),
       (5, 6, 'Черный'),
       (5, 7, 'Wrangler'),
       (6, 5, 'XL'),
       (6, 6, 'Красный'),
       (6, 7, 'Columbia');

-- Добавление данных в таблицу products для категории 'Книги'
INSERT INTO products (category_id, product_name, cost)
VALUES (3, 'Гарри Поттер и Философский Камень', 2950),
       (3, 'Мастер и Маргарита', 1250),
       (3, 'Преступление и наказание', 1890);

-- Привязка характеристик к продуктам для категории 'Книги'
INSERT INTO product_characteristic (product_id, characteristic_id, characteristic)
VALUES (7, 8, 'Джоан Роулинг'),
       (7, 9, 'Bloomsbury'),
       (7, 10, '1997'),
       (8, 8, 'Михаил Булгаков'),
       (8, 9, 'Художественная литература'),
       (8, 10, '1967'),
       (9, 8, 'Федор Достоевский'),
       (9, 9, 'Эксмо'),
       (9, 10, '1866');
