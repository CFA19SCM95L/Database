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
select * from customer;
-- Add new user to database: (pass last userID + 1, accountnum, pwd, cardNum)
insert into customer values ();
-- Update user information:
update customer
set password='password1', card_number='1122334455667788'
where member_id=1;
-- Get user profile:
select member_id, account_number, card_number from customer where account_number='user1' and password='password1';
-- Get order ID:
select order_id from order_description;
-- Add new order to database:(last order ID + 1 from above, pass member_id into product list, 'Credit' default value)
insert into order_description values(19, 1, 'Credit');
-- Get warehouse ID:(product_id='Product302' from user)
select warehouse_id, product_id from contain_online where product_id='Product302';
-- Add new data to sales_online:(warehouse_id from above, product_id='Product302' from user)
insert into sales_online values (19, 'Product302', 'W00000008', 3);
-- Update inventory from contain_online:(3 from user's selection)
update contain_online set inventory=inventory-3 where product_id='Product302'



-- Delete user order
	-- find product_id and order quantity (order_id (java code))
	select product_id, quantity from sales_online where order_id=1;
	-- update contain_online inventory (product_id and quantity (java code))
	update contain_online set inventory=inventory+1 where product_id='Product101'
	-- delete sales_online with user order
	delete from sales_online where order_id=1;
	-- delete order_description with user order 
	delete from order_description where order_id=1;





-- Delete user profile
	-- find user order list 
	select order_id from order_description where member_id=1;
	-- for every order (order_id = 1, 7, 13 (java code)){
		-- find product_id and order quantity (order_id (java code))
		select product_id, quantity from sales_online where order_id=1;
		-- update contain_online inventory (product_id and quantity (java code))
		update contain_online set inventory=inventory+1 where product_id='Product101'
		-- delete sales_online with user order
		delete from sales_online where order_id=1;
		-- delete order_description with user order 
		delete from order_description where order_id=1;
	}
	-- delete delivery (not in application)
	delete from delivery where member_id=1;
-- delete user
delete from customer where member_i=1;







