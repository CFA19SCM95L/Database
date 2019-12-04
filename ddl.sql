
CREATE TABLE Products (
  Product_ID varchar(10),
  Product_Name varchar(20),
  Category varchar(20),
  PRIMARY KEY (Product_ID)
);

CREATE TABLE Warehouse (
  Warehouse_ID varchar(10),
  Street varchar(20),
  Street_Number INT,
  City varchar(20),
  State varchar(2),
  Zipcode INT,
  PRIMARY KEY (Warehouse_ID)
);


CREATE TABLE Store (
  Store_ID varchar(10),
  Street varchar(20),
  Street_Number INT,
  City varchar(20),
  State varchar(2),
  Zipcode INT,
  PRIMARY KEY (Store_ID)
);

CREATE TABLE Manufacturer (
  Manufacturer_ID varchar(10),
  Company varchar(20),
  PRIMARY KEY (Manufacturer_ID)
);


CREATE TABLE Shipper (
  TrackingNumber varchar(10),
  Shipper_ID varchar(10),
  Shipper_Name varchar(20),
  Warehouse_ID varchar(10),
  PRIMARY KEY (TrackingNumber),
  FOREIGN KEY (Warehouse_ID) REFERENCES Warehouse(Warehouse_ID)
);


CREATE TABLE Customer (
  Member_ID INT, 
  Account_Number varchar(15), 
  Password varchar(15), 
  Card_Number varchar(16), 
  Customer_Type varchar(10) ,
  check(Customer_Type in ('Infrequent', 'Frequent')), 
  PRIMARY KEY (Member_ID)
);

CREATE TABLE Contain_Online (
  Warehouse_ID varchar(10),
  Product_ID varchar(10),
  Inventory INT,
  Price numeric(8,2),
  PRIMARY KEY (Warehouse_ID, Product_ID),
  FOREIGN KEY (Warehouse_ID) REFERENCES Warehouse(Warehouse_ID),
  FOREIGN KEY (Product_ID) REFERENCES Products(Product_ID)
);

CREATE TABLE Contain_Local (  
  Store_ID varchar(10),
  Product_ID varchar(10),
  Inventory INT,
  Price numeric(8,2),
  PRIMARY KEY (Store_ID, Product_ID),
  FOREIGN KEY (Product_ID) REFERENCES Products(Product_ID),
  FOREIGN KEY (Store_ID) REFERENCES Store(Store_ID)
);

CREATE TABLE Made (
  Product_ID varchar(10),
  Manufacturer_ID varchar(10),
  PRIMARY KEY (Product_ID, Manufacturer_ID),
  FOREIGN KEY (Product_ID) REFERENCES Products(Product_ID),
  FOREIGN KEY (Manufacturer_ID) REFERENCES Manufacturer(Manufacturer_ID)
);

CREATE TABLE Delivery (
  TrackingNumber varchar(10),
  Member_ID INT,
  PRIMARY KEY (TrackingNumber, Member_ID),
  FOREIGN KEY (TrackingNumber) REFERENCES Shipper(TrackingNumber),
  FOREIGN KEY (Member_ID) REFERENCES Customer(Member_ID)
);

CREATE TABLE Order_Description (
  Order_ID INT,
  Member_ID INT,
  Payment_Type varchar(6),
  check(Payment_Type in ('Cash', 'Credit', 'Debit')), 
  PRIMARY KEY (Order_ID),
  FOREIGN KEY (Member_ID) REFERENCES Customer(Member_ID)
);

CREATE TABLE Sales_Online (
  Order_ID INT,
  Product_ID varchar(10),
  Warehouse_ID varchar(10),
  Quantity INT,
  PRIMARY KEY (Order_ID, Product_ID, Warehouse_ID),
  FOREIGN KEY (Order_ID) REFERENCES Order_Description(Order_ID),
  FOREIGN KEY (Product_ID) REFERENCES Products(Product_ID),
  FOREIGN KEY (Warehouse_ID) REFERENCES Warehouse(Warehouse_ID)
);

CREATE TABLE Sales_Local (
  Order_ID INT,
  Product_ID varchar(10),
  Store_ID varchar(10),
  Quantity INT,
  PRIMARY KEY (Order_ID, Product_ID, Store_ID),
  FOREIGN KEY (Order_ID) REFERENCES Order_Description(Order_ID),
  FOREIGN KEY (Product_ID) REFERENCES Products(Product_ID),
  FOREIGN KEY (Store_ID) REFERENCES Store(Store_ID)
);

INSERT INTO Products VALUES ('Product101', 'Macbook Pro', 'Laptop');
INSERT INTO Products VALUES ('Product102', 'Pixelbook', 'Laptop');
INSERT INTO Products VALUES ('Product103', 'Dell XPS 13', 'Laptop');
INSERT INTO Products VALUES ('Product201', 'IPhone XR', 'Smartphone');
INSERT INTO Products VALUES ('Product202', 'Pixel 4', 'Smartphone');
INSERT INTO Products VALUES ('Product203', 'Galaxy S10', 'Smartphone');
INSERT INTO Products VALUES ('Product301', '70" Class LED', 'Smart TV');
INSERT INTO Products VALUES ('Product302', '65" Class OLED', 'Smart TV');
INSERT INTO Products VALUES ('Product303', '43" Class LED', 'Smart TV');
INSERT INTO Products VALUES ('Product401', 'PS4 Pro', 'Video Game');
INSERT INTO Products VALUES ('Product402', 'Nintendo Switch', 'Video Game');
INSERT INTO Products VALUES ('Product403', 'Xbox One', 'Video Game');
INSERT INTO Products VALUES ('Product501', 'Alpha a7 III', 'Camera');
INSERT INTO Products VALUES ('Product502', 'HERO7', 'Camera');
INSERT INTO Products VALUES ('Product503', 'D7500 DSLR', 'Camera');



insert into Warehouse VALUES ('W00000001', 'grand ave', 20, 'Chicago', 'IL', '60543');
insert into Warehouse VALUES ('W00000002', 'apple ave', 100, 'New York', 'NY', '32145');
insert into Warehouse VALUES ('W00000003', 'google ave', 220, 'San Francisco', 'CA', '60616');
insert into Warehouse VALUES ('W00000004', 'banana ave', 120, 'Chicago', 'IL', '61275');
insert into Warehouse VALUES ('W00000005', 'cat ave', 30, 'San Diago', 'CA', '75214');
insert into Warehouse VALUES ('W00000006', 'dog ave', 15, 'Los Angeles', 'CA', '83512');
insert into Warehouse VALUES ('W00000007', 'egg ave', 43, 'Seatle', 'WA', '44621');
insert into Warehouse VALUES ('W00000008', 'fish ave', 54, 'Chicago', 'IL', '86432');



insert into Store VALUES ('S00000001', 'hat ave' , 31, 'Chicago', 'IL', '23563');
insert into Store VALUES ('S00000002', 'ice cream ave' , 87, 'San Francisco', 'CA', '60616');
insert into Store VALUES ('S00000003', 'june ave' , 64, 'Miami', 'FL', '46363');
insert into Store VALUES ('S00000004', 'king ave' , 231, 'Chicago', 'IL', '74163');
insert into Store VALUES ('S00000005', 'lion ave' , 542, 'Washington', 'DC', '59563');
insert into Store VALUES ('S00000006', 'monkey ave' , 678, 'Palo Alto', 'CA', '60663');
insert into Store VALUES ('S00000007', 'notebook ave' , 88, 'Berkeley', 'CA', '60563');
insert into Store VALUES ('S00000008', 'orange ave' , 20, 'Oakland', 'CA', '63571');




INSERT INTO Manufacturer VALUES ('Manufac101','Apple');
INSERT INTO Manufacturer VALUES ('Manufac102','Dell');
INSERT INTO Manufacturer VALUES ('Manufac103','Google');
INSERT INTO Manufacturer VALUES ('Manufac104','Samsung');
INSERT INTO Manufacturer VALUES ('Manufac105','Toshiba');
INSERT INTO Manufacturer VALUES ('Manufac106','Sony');
INSERT INTO Manufacturer VALUES ('Manufac107','Nintendo');
INSERT INTO Manufacturer VALUES ('Manufac108','Microsoft');
INSERT INTO Manufacturer VALUES ('Manufac109','GoPro');
INSERT INTO Manufacturer VALUES ('Manufac110','Nikon');




insert into shipper VALUES ('0000001', 'S0000001' , 'UPS', 'W00000006');
insert into shipper VALUES ('0000002', 'S0000001' , 'UPS', 'W00000001');
insert into shipper VALUES ('0000003', 'S0000002' , 'Fedex', 'W00000002');
insert into shipper VALUES ('0000004', 'S0000003' , 'USPS', 'W00000003');
insert into shipper VALUES ('0000005', 'S0000001' , 'UPS', 'W00000008');
insert into shipper VALUES ('0000006', 'S0000003' , 'USPS', 'W00000004');
insert into shipper VALUES ('0000007', 'S0000002' , 'Fedex', 'W00000007');



insert into customer values (1, 'user1', 'password1', '1234567812345678', 'Frequent');
insert into customer values (2, 'user2', 'password2', '4567812345678123', 'Frequent');
insert into customer values (3, 'user3', 'password3', '4567812123345678', 'Frequent');
insert into customer values (4, 'user4', 'password4', '2345678123145678', 'Frequent');
insert into customer values (5, 'user5', 'password5', '7812345612345678', 'Frequent');
insert into customer values (6, 'user6', 'password6', '1238145672834567', 'Frequent');
insert into customer values (7, 'guest', 'guest', '****************', 'Infrequent')




insert into Contain_Online values ('W00000001', 'Product101', 2000, 1000);
insert into Contain_Online values ('W00000002', 'Product102', 3000, 2200);
insert into Contain_Online values ('W00000003', 'Product103', 1110, 900);
insert into Contain_Online values ('W00000004', 'Product201', 5000, 1300);
insert into Contain_Online values ('W00000005', 'Product202', 5420, 400);
insert into Contain_Online values ('W00000006', 'Product203', 5410, 200);
insert into Contain_Online values ('W00000007', 'Product301', 5320, 300);
insert into Contain_Online values ('W00000008', 'Product302', 7620, 700);
insert into Contain_Online values ('W00000001', 'Product303', 4320, 400);
insert into Contain_Online values ('W00000002', 'Product401', 6530, 299);
insert into Contain_Online values ('W00000003', 'Product402', 7560, 399);
insert into Contain_Online values ('W00000004', 'Product403', 4310, 299);
insert into Contain_Online values ('W00000005', 'Product501', 7450, 600);
insert into Contain_Online values ('W00000006', 'Product502', 4220, 800);
insert into Contain_Online values ('W00000007', 'Product503', 5240, 900);



insert into Contain_Local values ('S00000001', 'Product101', 40, 1000);
insert into Contain_Local values ('S00000002', 'Product102', 30, 2200);
insert into Contain_Local values ('S00000003', 'Product103', 50, 900);
insert into Contain_Local values ('S00000004', 'Product201', 30, 1300);
insert into Contain_Local values ('S00000005', 'Product202', 50, 400);
insert into Contain_Local values ('S00000006', 'Product203', 20, 200);
insert into Contain_Local values ('S00000007', 'Product301', 60, 300);
insert into Contain_Local values ('S00000008', 'Product302', 53, 700);
insert into Contain_Local values ('S00000001', 'Product303', 35, 400);
insert into Contain_Local values ('S00000002', 'Product401', 64, 299);
insert into Contain_Local values ('S00000003', 'Product402', 42, 399);
insert into Contain_Local values ('S00000004', 'Product403', 75, 299);
insert into Contain_Local values ('S00000005', 'Product501', 34, 600);
insert into Contain_Local values ('S00000006', 'Product502', 23, 800);
insert into Contain_Local values ('S00000007', 'Product503', 56, 900);



INSERT INTO Made VALUES ('Product101', 'Manufac101');
INSERT INTO Made VALUES ('Product102', 'Manufac103');
INSERT INTO Made VALUES ('Product103', 'Manufac102');
INSERT INTO Made VALUES ('Product201', 'Manufac101');
INSERT INTO Made VALUES ('Product202', 'Manufac103');
INSERT INTO Made VALUES ('Product203', 'Manufac104');
INSERT INTO Made VALUES ('Product301', 'Manufac106');
INSERT INTO Made VALUES ('Product302', 'Manufac106');
INSERT INTO Made VALUES ('Product303', 'Manufac105');
INSERT INTO Made VALUES ('Product401', 'Manufac106');
INSERT INTO Made VALUES ('Product402', 'Manufac107');
INSERT INTO Made VALUES ('Product403', 'Manufac108');
INSERT INTO Made VALUES ('Product501', 'Manufac106');
INSERT INTO Made VALUES ('Product502', 'Manufac109');
INSERT INTO Made VALUES ('Product503', 'Manufac110');



insert into delivery values ('0000001', 1);
insert into delivery values ('0000002', 2);
insert into delivery values ('0000003', 3);
insert into delivery values ('0000004', 3);
insert into delivery values ('0000005', 4);
insert into delivery values ('0000006', 1);
insert into delivery values ('0000007', 5);



insert into Order_Description values (1, 1, 'Credit');
insert into Order_Description values (2, 2, 'Debit');
insert into Order_Description values (3, 3, 'Credit');
insert into Order_Description values (4, 4, 'Debit');
insert into Order_Description values (5, 5, 'Credit');
insert into Order_Description values (6, 6, 'Debit');
insert into Order_Description values (7, 1, 'Credit');
insert into Order_Description values (8, 2, 'Debit');
insert into Order_Description values (9, 3, 'Credit');
insert into Order_Description values (10, 4, 'Debit');
insert into Order_Description values (11, 5, 'Credit');
insert into Order_Description values (12, 6, 'Debit');
insert into Order_Description values (13, 1, 'Credit');
insert into Order_Description values (14, 2, 'Debit');
insert into Order_Description values (15, 3, 'Credit');
insert into Order_Description values (16, 4, 'Debit');
insert into Order_Description values (17, 5, 'Credit');
insert into Order_Description values (18, 6, 'Debit');



insert into Sales_Online values (1, 'Product101', 'W00000001', 1);
insert into Sales_Online values (7, 'Product201', 'W00000004', 2);
insert into Sales_Online values (13, 'Product302', 'W00000008', 4);
insert into Sales_Online values (2, 'Product401', 'W00000002', 2);
insert into Sales_Online values (8, 'Product202', 'W00000005', 1);
insert into Sales_Online values (14, 'Product303', 'W00000001', 3);
insert into Sales_Online values (3, 'Product501', 'W00000005', 3);
insert into Sales_Online values (9, 'Product203', 'W00000006', 2);
insert into Sales_Online values (15, 'Product301', 'W00000007', 10);
insert into Sales_Online values (4, 'Product402', 'W00000003', 5);
insert into Sales_Online values (10, 'Product502', 'W00000006', 3);
insert into Sales_Online values (16, 'Product103', 'W00000003', 1);
insert into Sales_Online values (5, 'Product403', 'W00000004', 1);
insert into Sales_Online values (11, 'Product201', 'W00000004', 1);
insert into Sales_Online values (17, 'Product302', 'W00000008', 5);
insert into Sales_Online values (6, 'Product101', 'W00000001', 3);
insert into Sales_Online values (12, 'Product201', 'W00000004', 2);
insert into Sales_Online values (18, 'Product303', 'W00000001', 5);

