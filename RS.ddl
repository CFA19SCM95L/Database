
insert into member values (null, null, null, null, );
/*

insert into manufacturer values ('1','apple','12');
insert into product values ('12','mac','ele',1000);
insert into store values ('chicago', 'W','12', 30);
insert into customer values ('10', 'credit');

*/

create table product (product_ID varchar(10) primary key, product_name varchar(20), category varchar(15) , price numeric(8,2));
create table manufacturer (manufacturer_ID varchar(10) primary key, manufacturer_name varchar(20), product_ID varchar(10), foreign key (product_ID) references product  );
create table store (store_ID varchar(10) primary key, region char(1) , product_ID varchar(10), quantity  numeric(4,0) ,foreign key (product_ID) references product,check (region in('N','E','W','S')));
create table warehouse (warehouse_ID varchar(10) primary key , product_ID varchar(10), quantity numeric(4,0),foreign key (product_ID) references product );
create table shipper (shipper_ID varchar(5) primary key, shipper_name varchar(20), tracking_number varchar(5));
create table customer (customer_ID varchar(10) primary key, payment_type varchar(6), check (payment_type in ('credit','debit','cash')));
create table member (member_ID varchar(10) primary key, account_number varchar(15), password varchar(15), card_number varchar(16));
create table order_description (order_ID varchar(15) primary key, product_ID varchar(10), region char(1),quantity numeric(4,0)  ,customer_ID varchar(10), order_date date ,foreign key (product_ID) references product);
create table sales_data (region char(1), season varchar(6) ,product_ID varchar(10), quantity numeric(4,0), total_price numeric(10,2) ,primary key (region,season,product_ID), check (season in ('Spring','Summer','Fall','Winter')), foreign key (product_ID) references product);