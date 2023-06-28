drop table orders;
create table orders
(
    order_id    bigint primary key auto_increment,
    user_id     bigint,
    store_id    bigint,
    order_state varchar(255),
    price int
);

drop table orders_history;
create table orders_history
(
    order_history_id bigint primary key auto_increment,
    order_id         bigint,
    order_state      varchar(255)
);
drop table payment;
create table payment
(
    order_history_id bigint primary key auto_increment,
    store_id bigint,
    order_id bigint,
    price int
);
drop table store;
create table store(
    store_id bigint primary key auto_increment
);
drop table rider;
create table rider(
                      rider_id bigint primary key auto_increment,
                      store_id bigint,
                      order_id bigint
);
drop table fail_event;
create table fail_event(
                           fail_event_id bigint primary key auto_increment,
                           complete boolean,
                           json_object varchar(255)
)