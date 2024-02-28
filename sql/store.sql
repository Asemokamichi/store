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
    characteristic_name varchar(50) not null
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
    role         int2        not null,
    email        varchar(50) not null,
    password     varchar(20) not null,
    user_name    varchar(20) not null,
    user_surname varchar(30) not null
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
    status      int2 default 1,
    date_beg    date default now()
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
    review     text
);


insert into categories (category_name)
values ('Электроника'),
       ('Одежда'),
       ('Книги');


insert into products (category_id, product_name, cost)
values (1, 'Смартфон', 500),
       (1, 'Ноутбук', 1000),
       (2, 'Футболка', 20),
       (2, 'Джинсы', 50),
       (3, 'Java руководство', 30),
       (3, 'Python для начинающих', 25);


insert into characteristics (category_id, characteristic_name)
values (1, 'Производитель'),
       (1, 'Операционная система'),
       (2, 'Размер'),
       (2, 'Цвет'),
       (3, 'Автор');


insert into product_characteristic (product_id, characteristic_id, characteristic)
values (1, 1, 'Samsung'),
       (1, 2, 'Android'),
       (2, 1, 'HP'),
       (2, 2, 'Windows'),
       (3, 3, 'M'),
       (3, 4, 'Синий'),
       (4, 3, 'L'),
       (4, 4, 'Черный'),
       (5, 5, 'Шилдт Г.'),
       (6, 5, 'Лутц М.');


insert into users (role, email, password, user_name, user_surname)
values (1, 'user1@gmail.com', 'password1', 'John', 'Doe'),
       (2, 'user2@gmail.com', 'password2', 'Jane', 'Smith'),
       (1, 'user3@gmail.com', 'password3', 'Alice', 'Johnson');


insert into carts (user_id, product_id, count)
values (1, 1, 2),
       (1, 3, 1),
       (2, 2, 1),
       (3, 5, 1),
       (3, 6, 2);


insert into purchases (user_id, status, date_beg)
values (3, 1, now() - interval '3 days'),
       (1, 2, now() - interval '2 days'),
       (2, 1, now() - interval '1 day');


insert into orders (product_id, purchase_id, count)
values (1, 1, 2),
       (3, 1, 1),
       (2, 2, 1),
       (5, 3, 1),
       (6, 3, 2);


insert into reviews (user_id, product_id, review, score)
values
    (1, 1, 'Отличный смартфон, рекомендую!', 5),
    (2, 3, 'Футболка подошла по размеру, спасибо!', 4),
    (3, 5, 'Отличная книга для начинающих.', 5),
    (1, 2, 'Ноутбук оказался мощным и надежным.', 5),
    (2, 6, 'Python для начинающих - отличный выбор для изучения языка.', 4),
    (3, 4, 'Джинсы качественные, но немного велики в талии.', 4),
    (1, 3, 'Футболка отличного качества, материал приятный к телу.', 5),
    (2, 1, 'Смартфон имеет отличную камеру и производительность.', 5),
    (3, 2, 'Ноутбук был доставлен вовремя, упаковка целая.', 5),
    (1, 5, 'Java руководство - отличная книга для изучения языка.', 5);



insert into reviews(user_id, product_id, review, score)
values (3, 1,'не тот товар', 1);

insert into products (category_id, product_name, cost)
values
    (1, 'Планшет', 300),
    (1, 'Наушники', 50),
    (2, 'Платье', 40),
    (2, 'Костюм', 70),
    (3, 'SQL для начинающих', 35),
    (3, 'JavaScript: Полное руководство', 40);
;


insert into characteristics (category_id, characteristic_name)
values
    (1, 'Емкость батареи'),
    (1, 'Размер экрана'),
    (2, 'Материал'),
    (2, 'Сезонность'),
    (3, 'Издательство'),
    (3, 'Авторство');


insert into product_characteristic (product_id, characteristic_id, characteristic)
values
    (7, 1, '4000 мАч'),
    (7, 2, '10 дюймов'),
    (8, 1, '20 часов'),
    (8, 2, 'Bluetooth'),
    (9, 3, 'Хлопок'),
    (9, 4, 'Летний'),
    (10, 5, 'Reilly'),
    (10, 6, 'Джон Дакетт');


insert into products (category_id, product_name, cost)
values
    (1, 'Фотоаппарат', 400),
    (1, 'Портативное зарядное устройство', 25),
    (2, 'Юбка', 30),
    (2, 'Рубашка', 45),
    (3, 'HTML и CSS: Дизайн и разметка веб-страниц', 30),
    (3, 'C# для начинающих', 35);

insert into characteristics (category_id, characteristic_name)
values
    (1, 'Разрешение'),
    (1, 'Тип матрицы'),
    (2, 'Длина'),
    (2, 'Стиль'),
    (3, 'Издатель'),
    (3, 'Язык программирования');


insert into product_characteristic (product_id, characteristic_id, characteristic)
values
    (13, 7, '24 мегапикселя'),
    (13, 8, 'APS-C'),
    (14, 7, '20000 мАч'),
    (14, 8, 'USB-C'),
    (15, 9, 'Макси'),
    (15, 10, 'Кэжуал'),
    (16, 11, 'Для детей'),
    (16, 12, 'Спортивный'),
    (17, 13, 'Wiley'),
    (17, 14, 'JavaScript');
















