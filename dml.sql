-- Product List:
select product_id, product_name, category, inventory, price from products natural join contain_online;
-- Order List:
select order_id, product_name, quantity, (price*quantity) as total_amount from order_description natural join sales_online natural join products natural join contain_online;
-- User_Order:
select member_id, order_id, product_name, quantity, (price*quantity) as total_amount 
from order_description natural join sales_online natural join products natural join contain_online
where member_id = 1;
-- User List:
select member_id, account_number, password, card_number from customer;
-- Shipper List:
select trackingnumber, shipper_id, shipper_name from shipper;
-- Sales_Data List:
select product_name, sum(quantity) as total_quantity, sum(quantity)*price as sales_amount from order_description natural join sales_online natural join products natural join contain_online group by product_name, price;
-- Manufacturer List:
select manufacturer_id, company, product_name from manufacturer natural join made natural join products;
-- Get user ID:
select count(*) from customer;
-- Add new user to database:
insert into customer values ();
-- Update user information:
update customer
set password='password1', card_number='1122334455667788'
where member_id=1;
-- Get user profile:
select member_id, account_number, card_number from customer where account_number='user1' and password='password1';
-- Get order ID:
select count(*) from order_description;
-- Add new order to database:(order_id from above, pass member_id into product list, 'Credit' default value)
insert into order_description values(19, 1, 'Credit');
-- Get warehouse ID:(product_id='Product302' from user)
select warehouse_id, product_id from contain_online where product_id='Product302';
-- Add new data to sales_online:(warehouse_id from above, product_id='Product302' from user)
insert into sales_online values (19, 'Product302', 'W00000008', 3);
-- Update inventory from contain_online:(3 from user's selection)
update contain_online set inventory=inventory-3 where product_id='Product302'






