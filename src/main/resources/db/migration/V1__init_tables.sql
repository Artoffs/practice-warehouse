create table pickup_point(
    id serial primary key,
    address varchar(255)
);

create table supplier(
    id bigserial primary key,
    name varchar(100) not null,
    email varchar(100) not null,
    phone varchar(20) not null
);

create table product(
        id bigserial primary key,
        supplier_id bigint references supplier(id) on delete set null,
        name varchar(100),
        description varchar(255),
        price decimal(10, 2) not null default 0.0
);

create table stock(
    id bigserial primary key,
    product_id bigint references product(id) on delete cascade unique,
    quantity int
);

create table shipment(
    id bigserial primary key,
    pickup_point_id bigint references pickup_point(id) on delete set null,
    status varchar(50),
    created_at DATE default now()
);

create table customer_order(
    id bigserial primary key,
    shipment_id bigint references shipment(id) on delete set null,
    total_price decimal(10, 2)
);

create table order_items(
    id bigserial primary key,
    order_id bigint references customer_order(id) on delete cascade,
    product_id bigint references product(id) on delete set null,
    quantity int,
    price_at_shipment decimal(10,2)
);