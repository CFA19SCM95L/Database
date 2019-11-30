package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    private static final String TAG = "Database ***";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "BestBuy";

    public SQLiteDatabase database;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        database = getWritableDatabase();
    }

    //create table and insert default
    @Override
    public void onCreate(SQLiteDatabase db) {

        createTable(db);
        insertDefault(db);


        Log.d(TAG, "onCreate: ***");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    protected void countAll() {
        Cursor cursor = database.rawQuery("select * from Products", null);
        Log.d(TAG, "countAll: Products " + cursor.getCount());

        cursor = database.rawQuery("select * from Store", null);
        Log.d(TAG, "countAll: Store" + cursor.getCount());
        cursor = database.rawQuery("select * from Manufacturer", null);
        Log.d(TAG, "countAll: Manufacturer" + cursor.getCount());
        cursor = database.rawQuery("select * from shipper", null);
        Log.d(TAG, "countAll: shipper" + cursor.getCount());
        cursor = database.rawQuery("select * from customer", null);
        Log.d(TAG, "countAll: customer" + cursor.getCount());
        cursor = database.rawQuery("select * from Contain_Online", null);
        Log.d(TAG, "countAll: Contain_Online" + cursor.getCount());
        cursor = database.rawQuery("select * from Contain_Local", null);
        Log.d(TAG, "countAll: Contain_Local" + cursor.getCount());
        cursor = database.rawQuery("select * from Made", null);
        Log.d(TAG, "countAll: Made" + cursor.getCount());
        cursor = database.rawQuery("select * from delivery", null);
        Log.d(TAG, "countAll: delivery" + cursor.getCount());
        cursor = database.rawQuery("select * from Order_Description", null);
        Log.d(TAG, "countAll: Order_Description" + cursor.getCount());

        cursor = database.rawQuery("select * from Sales_Online", null);
        Log.d(TAG, "countAll: Sales_Online" + cursor.getCount());


    }

    protected ArrayList<String[]> loadUser() {
        ArrayList<String[]> users = new ArrayList<>();

        Cursor cursor = database.rawQuery("select member_id, account_number, password, card_number from Customer", null);
        if (cursor != null) {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {
                String member_id = cursor.getString(0);
                String account_number = cursor.getString(1);
                String password = cursor.getString(2);
                String card_number = cursor.getString(3);
                users.add(new String[]{member_id, account_number, password, card_number});

                Log.d(TAG, "loadUser: " + member_id + " " + account_number + " " + password + " " + card_number);

                cursor.moveToNext();
            }
            cursor.close();
        }
        return users;
    }


    protected ArrayList<String[]> loadProduct() {
        ArrayList<String[]> product = new ArrayList<>();

        Cursor cursor = database.rawQuery("select product_id, product_name, category, inventory, price from products natural join contain_online", null);

        if (cursor != null) {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {
                Log.d(TAG, "loadProduct: ***");

                String product_id = cursor.getString(0);
                String product_name = cursor.getString(1);
                String category = cursor.getString(2);
                String inventory = cursor.getString(3);
                String price = cursor.getString(4);
                product.add(new String[]{product_id, product_name, category, inventory, price});


                Log.d(TAG, "loadProduct: " + product_id + " " + product_name + " " + category + " " + inventory + " " + price);

                cursor.moveToNext();
            }
            cursor.close();
        }
        return product;
    }

    protected ArrayList<String[]> loadOrder() {
        ArrayList<String[]> order = new ArrayList<>();
        Cursor cursor = database.rawQuery("select order_id, product_name, quantity, (price*quantity) as total_amount from order_description natural join sales_online natural join products natural join contain_online order by order_id", null);

        if (cursor != null) {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {
                Log.d(TAG, "loadOrder: ***");

                String order_id = cursor.getString(0);
                String product_name = cursor.getString(1);
                String quantity = cursor.getString(2);
                String total_amount = cursor.getString(3);

                order.add(new String[]{order_id, product_name, quantity, total_amount});


                cursor.moveToNext();
            }
            cursor.close();
        }
        return order;
    }


    protected ArrayList<String[]> loadShipper() {
        ArrayList<String[]> shipper = new ArrayList<>();
        Cursor cursor = database.rawQuery("select trackingnumber, shipper_id, shipper_name from shipper", null);

        if (cursor != null) {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {

                String trackingnumber = cursor.getString(0);
                String shipper_id = cursor.getString(1);
                String shipper_name = cursor.getString(2);

                shipper.add(new String[]{trackingnumber, shipper_id, shipper_name});

                cursor.moveToNext();
            }
            cursor.close();
        }
        return shipper;
    }

    protected ArrayList<String[]> loadSalesData() {
        ArrayList<String[]> data = new ArrayList<>();
        Cursor cursor = database.rawQuery("select product_name, sum(quantity) as total_quantity, sum(quantity)*price as sales_amount from order_description natural join sales_online natural join products natural join contain_online group by product_name, price order by sales_amount", null);

        if (cursor != null) {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {

                String product_name = cursor.getString(0);
                String total_quantity = cursor.getString(1);
                String sales_amount = cursor.getString(2);
                data.add(new String[]{product_name, total_quantity, sales_amount});


                cursor.moveToNext();
            }
            cursor.close();
        }
        return data;
    }

    protected ArrayList<String[]> loadManu() {
        ArrayList<String[]> manu = new ArrayList<>();
        Cursor cursor = database.rawQuery("select manufacturer_id, company, product_name from manufacturer natural join made natural join products order by manufacturer_id", null);

        if (cursor != null) {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {

                String manufacturer_id = cursor.getString(0);
                String company = cursor.getString(1);
                String product_name = cursor.getString(2);


                manu.add(new String[] {manufacturer_id, company, product_name});
                cursor.moveToNext();
            }
            cursor.close();
        }
        return manu;
    }




    public void addUser(String username, String password, String card) {

        Cursor cursor = database.rawQuery("select * from customer", null);
        Log.d(TAG, "addUser: *** count member: " + cursor.getCount());
        String id = cursor.getCount() + 1 + "";

        ContentValues values = new ContentValues();
        values.put("Member_ID", id);
        values.put("Account_Number", username);
        values.put("Password", password);
        values.put("Card_Number", card);
        values.put("Customer_Type", "Frequent");

        database.insert("Customer", null, values);

    }

    protected ArrayList<String[]> loadUsrOrder(String memberID) {
        ArrayList<String[]> order = new ArrayList<>();

        Cursor cursor = database.rawQuery("select member_id, order_id, product_name, quantity, (price*quantity) as total_amount from order_description natural join sales_online natural join products natural join contain_online where member_id = " + memberID, null);

        if (cursor != null) {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {

                String member_id = cursor.getString(0);
                String order_id = cursor.getString(1);
                String product_name = cursor.getString(2);
                String quantity = cursor.getString(3);
                String total_amount = cursor.getString(4);

                order.add(new String[] {order_id, product_name, quantity, total_amount});

                cursor.moveToNext();
            }
            cursor.close();
        }
        return order;
    }

    protected String[] getUserProfile(String userID) {
        String sql = "select member_id, account_number, card_number from customer where account_number= " + "'" + userID + "'";

        Log.d(TAG, "getUserProfile: ***" + sql);

        String[] ret = new String[3];

        Cursor cursor = database.rawQuery(sql, null);
        Log.d(TAG, "getUserProfile: " + cursor.getCount());
        if (cursor != null) {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {

                String member_id = cursor.getString(0);
                ret[0] = member_id;
                String account_number = cursor.getString(1);
                ret[1] = account_number;
                String card_number = cursor.getString(2);
                ret[2] = card_number;
                Log.d(TAG, "getUserProfile: " + member_id + " " + account_number + " " + card_number);

                cursor.moveToNext();
            }
            cursor.close();
        }
        return ret;
    }

    public void updateProfile(String memberID, String userName, String password, String cardNumber) {

        ContentValues values = new ContentValues();
        values.put("password", password);
        values.put("card_number", cardNumber);

        database.update("customer", values, "member_id=" + memberID, null);
    }

    public void userbuy(String memberID, String productID, int quantity) {
        //ger orderID
        Cursor cursor = database.rawQuery("select * from order_description", null);
        String orderID = cursor.getCount() + 1 + "";
        //sql
        String sql = "insert into order_description values(" + orderID + "," + memberID + ", 'Credit')";
        database.execSQL(sql);

        //get warehouseID
        sql = "select warehouse_id, product_id from contain_online where product_id='" + productID + "'";
        cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        String warehouse_id = cursor.getString(0);

        //sql
        sql = "insert into sales_online values ( " + orderID + ", '" + productID + "', '" + warehouse_id + "', " + quantity + ")";
        database.execSQL(sql);

        // update productList

        database.execSQL("update contain_online set inventory=inventory- " + quantity + " where product_id='" + productID + "'");

    }

    public boolean checkUser(String username, String password) {
        String sql = "select * from customer where Account_Number = '" + username + "' and Password = '" + password + "'";
        Cursor cursor= database.rawQuery(sql, null);
        int res = cursor.getCount();
        if (res == 0) {
            return false;
        } else {
            return true;
        }
    }

    protected void deleteUserOrder(String orderID) {
        String sql = "select product_id, quantity from sales_online where order_id=" + orderID;
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        String productID = cursor.getString(0);
        int quantity = cursor.getInt(1);
        sql = "update contain_online set inventory=inventory+" + quantity + " where product_id='" + productID + "'";
        database.execSQL(sql);
        sql = "delete from sales_online where order_id=" +orderID;
        database.execSQL(sql);
        sql = "delete from order_description where order_id=" + orderID;
        database.execSQL(sql);

    }

    protected void deleteUser(String userID) {
        String sql = "select order_id from order_description where member_id=" + userID;
        Cursor cursor = database.rawQuery(sql, null);
        ArrayList<String> orders = new ArrayList<>();
        if (cursor != null) {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {

                String order_ID = cursor.getString(0);
                orders.add(order_ID);
                cursor.moveToNext();
            }
            cursor.close();
        }

        for (String orderID : orders) {
            String sql1 = "select product_id, quantity from sales_online where order_id=" + orderID;
            Cursor cursor1 = database.rawQuery(sql1, null);
            Log.d(TAG, "deleteUser: " + cursor1.getCount() + " " );
            cursor1.moveToFirst();
            Log.d(TAG, "deleteUser: " + cursor1.getCount() + " " );

            String productID = cursor1.getString(0);
            int quantity = cursor1.getInt(1);
            sql = "update contain_online set inventory=inventory+" + quantity + " where product_id='" + productID + "'";
            database.execSQL(sql);
            sql = "delete from sales_online where order_id=" +orderID;
            database.execSQL(sql);
            sql = "delete from order_description where order_id=" + orderID;
            database.execSQL(sql);
        }
        database.execSQL("delete from delivery where member_id=" + userID);
        database.execSQL("delete from customer where member_id=" + userID);

    }


    private void createTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Products(Product_ID varchar(10),Product_Name varchar(20),Category varchar(20),PRIMARY KEY (Product_ID))");
        db.execSQL("CREATE TABLE Warehouse ( Warehouse_ID varchar(10), Street varchar(20), Street_Number INT, City varchar(20), State varchar(2), Zipcode INT, PRIMARY KEY (Warehouse_ID) )");
        db.execSQL("CREATE TABLE Store ( Store_ID varchar(10), Street varchar(20), Street_Number INT, City varchar(20), State varchar(2), Zipcode INT, PRIMARY KEY (Store_ID) )");
        db.execSQL("CREATE TABLE Manufacturer ( Manufacturer_ID varchar(10), Company varchar(20), PRIMARY KEY (Manufacturer_ID) )");
        db.execSQL("CREATE TABLE Shipper ( TrackingNumber varchar(10), Shipper_ID varchar(10), Shipper_Name varchar(20), Warehouse_ID varchar(10), PRIMARY KEY (TrackingNumber), FOREIGN KEY (Warehouse_ID) REFERENCES Warehouse(Warehouse_ID) )");
        db.execSQL("CREATE TABLE Customer( Member_ID INT, Account_Number varchar(15), Password varchar(15), Card_Number varchar(16), Customer_Type varchar(10) , check(Customer_Type in ('Infrequent', 'Frequent')), PRIMARY KEY (Member_ID) )");
        db.execSQL("CREATE TABLE Contain_Online ( Warehouse_ID varchar(10), Product_ID varchar(10), Inventory INT, Price numeric(8,2), PRIMARY KEY (Warehouse_ID, Product_ID), FOREIGN KEY (Warehouse_ID) REFERENCES Warehouse(Warehouse_ID), FOREIGN KEY (Product_ID) REFERENCES Products(Product_ID) )");
        db.execSQL("CREATE TABLE Contain_Local ( Product_ID varchar(10), Store_ID varchar(10), Inventory INT, Price numeric(8,2), PRIMARY KEY (Product_ID, Store_ID), FOREIGN KEY (Product_ID) REFERENCES Products(Product_ID), FOREIGN KEY (Store_ID) REFERENCES Store(Store_ID) )");
        db.execSQL("CREATE TABLE Made ( Product_ID varchar(10), Manufacturer_ID varchar(10), PRIMARY KEY (Product_ID, Manufacturer_ID), FOREIGN KEY (Product_ID) REFERENCES Products(Product_ID), FOREIGN KEY (Manufacturer_ID) REFERENCES Manufacturer(Manufacturer_ID) )");
        db.execSQL("CREATE TABLE Delivery ( TrackingNumber varchar(10), Member_ID INT, PRIMARY KEY (TrackingNumber, Member_ID), FOREIGN KEY (TrackingNumber) REFERENCES Shipper(TrackingNumber), FOREIGN KEY (Member_ID) REFERENCES Customer(Member_ID) )");
        db.execSQL("CREATE TABLE Order_Description ( Order_ID INT, Member_ID INT, Payment_Type varchar(6), check(Payment_Type in ('Cash', 'Credit', 'Debit')), PRIMARY KEY (Order_ID), FOREIGN KEY (Member_ID) REFERENCES Customer(Member_ID) )");
        db.execSQL("CREATE TABLE Sales_Online ( Order_ID INT, Product_ID varchar(10), Warehouse_ID varchar(10), Quantity INT, PRIMARY KEY (Order_ID, Product_ID, Warehouse_ID), FOREIGN KEY (Order_ID) REFERENCES Order_Description(Order_ID), FOREIGN KEY (Product_ID) REFERENCES Products(Product_ID), FOREIGN KEY (Warehouse_ID) REFERENCES Warehouse(Warehouse_ID) )");

    }

    private void insertDefault(SQLiteDatabase db) {
        Log.d(TAG, "insertDefault: ***start");
        db.execSQL("INSERT INTO Products VALUES ('Product101', 'Macbook Pro', 'Laptop')");
        db.execSQL("INSERT INTO Products VALUES ('Product102', 'Pixelbook', 'Laptop')");
        db.execSQL("INSERT INTO Products VALUES ('Product103', 'Dell XPS 13', 'Laptop')");
        db.execSQL("INSERT INTO Products VALUES ('Product201', 'IPhone XR', 'Smartphone')");
        db.execSQL("INSERT INTO Products VALUES ('Product202', 'Pixel 4', 'Smartphone')");
        db.execSQL("INSERT INTO Products VALUES ('Product203', 'Galaxy S10', 'Smartphone')");
        db.execSQL("INSERT INTO Products VALUES ('Product301', '70\" Class LED', 'Smart TV')");
        db.execSQL("INSERT INTO Products VALUES ('Product302', '65\" Class OLED', 'Smart TV')");
        db.execSQL("INSERT INTO Products VALUES ('Product303', '43\" Class LED', 'Smart TV')");
        db.execSQL("INSERT INTO Products VALUES ('Product401', 'PS4 Pro', 'Video Game')");
        db.execSQL("INSERT INTO Products VALUES ('Product402', 'Nintendo Switch', 'Video Game')");
        db.execSQL("INSERT INTO Products VALUES ('Product403', 'Xbox One', 'Video Game')");
        db.execSQL("INSERT INTO Products VALUES ('Product501', 'Alpha a7 III', 'Camera')");
        db.execSQL("INSERT INTO Products VALUES ('Product502', 'HERO7', 'Camera')");
        db.execSQL("INSERT INTO Products VALUES ('Product503', 'D7500 DSLR', 'Camera')");

        db.execSQL("insert into Warehouse VALUES ('W00000001', 'grand ave', 20, 'Chicago', 'IL', '60543') ");
        db.execSQL("insert into Warehouse VALUES ('W00000002', 'apple ave', 100, 'New York', 'NY', '32145') ");
        db.execSQL("insert into Warehouse VALUES ('W00000003', 'google ave', 220, 'San Francisco', 'CA', '60616') ");
        db.execSQL("insert into Warehouse VALUES ('W00000004', 'banana ave', 120, 'Chicago', 'IL', '61275') ");
        db.execSQL("insert into Warehouse VALUES ('W00000005', 'cat ave', 30, 'San Diago', 'CA', '75214') ");
        db.execSQL("insert into Warehouse VALUES ('W00000006', 'dog ave', 15, 'Los Angeles', 'CA', '83512') ");
        db.execSQL("insert into Warehouse VALUES ('W00000007', 'egg ave', 43, 'Seatle', 'WA', '44621') ");
        db.execSQL("insert into Warehouse VALUES ('W00000008', 'fish ave', 54, 'Chicago', 'IL', '86432') ");

        db.execSQL("insert into Store VALUES ('S00000001', 'hat ave' , 31, 'Chicago', 'IL', '23563') ");
        db.execSQL("insert into Store VALUES ('S00000002', 'ice cream ave' , 87, 'San Francisco', 'CA', '60616') ");
        db.execSQL("insert into Store VALUES ('S00000003', 'june ave' , 64, 'Miami', 'FL', '46363') ");
        db.execSQL("insert into Store VALUES ('S00000004', 'king ave' , 231, 'Chicago', 'IL', '74163') ");
        db.execSQL("insert into Store VALUES ('S00000005', 'lion ave' , 542, 'Washington', 'DC', '59563') ");
        db.execSQL("insert into Store VALUES ('S00000006', 'monkey ave' , 678, 'Palo Alto', 'CA', '60663') ");
        db.execSQL("insert into Store VALUES ('S00000007', 'notebook ave' , 88, 'Berkeley', 'CA', '60563') ");
        db.execSQL("insert into Store VALUES ('S00000008', 'orange ave' , 20, 'Oakland', 'CA', '63571') ");

        db.execSQL("INSERT INTO Manufacturer VALUES ('Manufac101','Apple') ");
        db.execSQL("INSERT INTO Manufacturer VALUES ('Manufac102','Dell') ");
        db.execSQL("INSERT INTO Manufacturer VALUES ('Manufac103','Google') ");
        db.execSQL("INSERT INTO Manufacturer VALUES ('Manufac104','Samsung') ");
        db.execSQL("INSERT INTO Manufacturer VALUES ('Manufac105','Toshiba') ");
        db.execSQL("INSERT INTO Manufacturer VALUES ('Manufac106','Sony') ");
        db.execSQL("INSERT INTO Manufacturer VALUES ('Manufac107','Nintendo') ");
        db.execSQL("INSERT INTO Manufacturer VALUES ('Manufac108','Microsoft') ");
        db.execSQL("INSERT INTO Manufacturer VALUES ('Manufac109','GoPro') ");
        db.execSQL("INSERT INTO Manufacturer VALUES ('Manufac110','Nikon') ");

        db.execSQL("insert into shipper VALUES ('0000001', 'S0000001' , 'UPS', 'W00000006') ");
        db.execSQL("insert into shipper VALUES ('0000002', 'S0000001' , 'UPS', 'W00000001') ");
        db.execSQL("insert into shipper VALUES ('0000003', 'S0000002' , 'Fedex', 'W00000002') ");
        db.execSQL("insert into shipper VALUES ('0000004', 'S0000003' , 'USPS', 'W00000003') ");
        db.execSQL("insert into shipper VALUES ('0000005', 'S0000001' , 'UPS', 'W00000008') ");
        db.execSQL("insert into shipper VALUES ('0000006', 'S0000003' , 'USPS', 'W00000004') ");
        db.execSQL("insert into shipper VALUES ('0000007', 'S0000002' , 'Fedex', 'W00000007') ");

        db.execSQL("insert into customer values (1, 'user1', 'password1', '1234567812345678', 'Frequent') ");
        db.execSQL("insert into customer values (2, 'user2', 'password2', '4567812345678123', 'Frequent') ");
        db.execSQL("insert into customer values (3, 'user3', 'password3', '4567812123345678', 'Frequent') ");
        db.execSQL("insert into customer values (4, 'user4', 'password4', '2345678123145678', 'Frequent') ");
        db.execSQL("insert into customer values (5, 'user5', 'password5', '7812345612345678', 'Frequent') ");
        db.execSQL("insert into customer values (6, 'user6', 'password6', '1238145672834567', 'Frequent') ");

        db.execSQL("insert into Contain_Online values ('W00000001', 'Product101', 2000, 1000) ");
        db.execSQL("insert into Contain_Online values ('W00000002', 'Product102', 3000, 2200) ");
        db.execSQL("insert into Contain_Online values ('W00000003', 'Product103', 1110, 900) ");
        db.execSQL("insert into Contain_Online values ('W00000004', 'Product201', 5000, 1300) ");
        db.execSQL("insert into Contain_Online values ('W00000005', 'Product202', 5420, 400) ");
        db.execSQL("insert into Contain_Online values ('W00000006', 'Product203', 5410, 200) ");
        db.execSQL("insert into Contain_Online values ('W00000007', 'Product301', 5320, 300) ");
        db.execSQL("insert into Contain_Online values ('W00000008', 'Product302', 7620, 700) ");
        db.execSQL("insert into Contain_Online values ('W00000001', 'Product303', 4320, 400) ");
        db.execSQL("insert into Contain_Online values ('W00000002', 'Product401', 6530, 299) ");
        db.execSQL("insert into Contain_Online values ('W00000003', 'Product402', 7560, 399) ");
        db.execSQL("insert into Contain_Online values ('W00000004', 'Product403', 4310, 299) ");
        db.execSQL("insert into Contain_Online values ('W00000005', 'Product501', 7450, 600) ");
        db.execSQL("insert into Contain_Online values ('W00000006', 'Product502', 4220, 800) ");
        db.execSQL("insert into Contain_Online values ('W00000007', 'Product503', 5240, 900) ");


        db.execSQL("insert into Contain_Local values ('S00000001', 'Product101', 40, 1000) ");
        db.execSQL("insert into Contain_Local values ('S00000002', 'Product102', 30, 2200) ");
        db.execSQL("insert into Contain_Local values ('S00000003', 'Product103', 50, 900) ");
        db.execSQL("insert into Contain_Local values ('S00000004', 'Product201', 30, 1300) ");
        db.execSQL("insert into Contain_Local values ('S00000005', 'Product202', 50, 400) ");
        db.execSQL("insert into Contain_Local values ('S00000006', 'Product203', 20, 200) ");
        db.execSQL("insert into Contain_Local values ('S00000007', 'Product301', 60, 300) ");
        db.execSQL("insert into Contain_Local values ('S00000008', 'Product302', 53, 700) ");
        db.execSQL("insert into Contain_Local values ('S00000001', 'Product303', 35, 400) ");
        db.execSQL("insert into Contain_Local values ('S00000002', 'Product401', 64, 299) ");
        db.execSQL("insert into Contain_Local values ('S00000003', 'Product402', 42, 399) ");
        db.execSQL("insert into Contain_Local values ('S00000004', 'Product403', 75, 299) ");
        db.execSQL("insert into Contain_Local values ('S00000005', 'Product501', 34, 600) ");
        db.execSQL("insert into Contain_Local values ('S00000006', 'Product502', 23, 800) ");
        db.execSQL("insert into Contain_Local values ('S00000007', 'Product503', 56, 900) ");

        db.execSQL("INSERT INTO Made VALUES ('Product101', 'Manufac101') ");
        db.execSQL("INSERT INTO Made VALUES ('Product102', 'Manufac103') ");
        db.execSQL("INSERT INTO Made VALUES ('Product103', 'Manufac102') ");
        db.execSQL("INSERT INTO Made VALUES ('Product201', 'Manufac101') ");
        db.execSQL("INSERT INTO Made VALUES ('Product202', 'Manufac103') ");
        db.execSQL("INSERT INTO Made VALUES ('Product203', 'Manufac104') ");
        db.execSQL("INSERT INTO Made VALUES ('Product301', 'Manufac106') ");
        db.execSQL("INSERT INTO Made VALUES ('Product302', 'Manufac106') ");
        db.execSQL("INSERT INTO Made VALUES ('Product303', 'Manufac105') ");
        db.execSQL("INSERT INTO Made VALUES ('Product401', 'Manufac106') ");
        db.execSQL("INSERT INTO Made VALUES ('Product402', 'Manufac107') ");
        db.execSQL("INSERT INTO Made VALUES ('Product403', 'Manufac108') ");
        db.execSQL("INSERT INTO Made VALUES ('Product501', 'Manufac106') ");
        db.execSQL("INSERT INTO Made VALUES ('Product502', 'Manufac109') ");
        db.execSQL("INSERT INTO Made VALUES ('Product503', 'Manufac110') ");

        db.execSQL("insert into delivery values ('0000001', 1) ");
        db.execSQL("insert into delivery values ('0000002', 2) ");
        db.execSQL("insert into delivery values ('0000003', 3) ");
        db.execSQL("insert into delivery values ('0000004', 3) ");
        db.execSQL("insert into delivery values ('0000005', 4) ");
        db.execSQL("insert into delivery values ('0000006', 1) ");
        db.execSQL("insert into delivery values ('0000007', 5) ");

        db.execSQL("insert into Order_Description values (1, 1, 'Credit') ");
        db.execSQL("insert into Order_Description values (2, 2, 'Debit') ");
        db.execSQL("insert into Order_Description values (3, 3, 'Credit') ");
        db.execSQL("insert into Order_Description values (4, 4, 'Debit') ");
        db.execSQL("insert into Order_Description values (5, 5, 'Credit') ");
        db.execSQL("insert into Order_Description values (6, 6, 'Debit') ");
        db.execSQL("insert into Order_Description values (7, 1, 'Credit') ");
        db.execSQL("insert into Order_Description values (8, 2, 'Debit') ");
        db.execSQL("insert into Order_Description values (9, 3, 'Credit') ");
        db.execSQL("insert into Order_Description values (10, 4, 'Debit') ");
        db.execSQL("insert into Order_Description values (11, 5, 'Credit') ");
        db.execSQL("insert into Order_Description values (12, 6, 'Debit') ");
        db.execSQL("insert into Order_Description values (13, 1, 'Credit') ");
        db.execSQL("insert into Order_Description values (14, 2, 'Debit') ");
        db.execSQL("insert into Order_Description values (15, 3, 'Credit') ");
        db.execSQL("insert into Order_Description values (16, 4, 'Debit') ");
        db.execSQL("insert into Order_Description values (17, 5, 'Credit') ");
        db.execSQL("insert into Order_Description values (18, 6, 'Debit') ");

        db.execSQL("insert into Sales_Online values (1, 'Product101', 'W00000001', 1) ");
        db.execSQL("insert into Sales_Online values (7, 'Product201', 'W00000004', 2) ");
        db.execSQL("insert into Sales_Online values (13, 'Product302', 'W00000008', 4) ");
        db.execSQL("insert into Sales_Online values (2, 'Product401', 'W00000002', 2) ");
        db.execSQL("insert into Sales_Online values (8, 'Product202', 'W00000005', 1) ");
        db.execSQL("insert into Sales_Online values (14, 'Product303', 'W00000001', 3) ");
        db.execSQL("insert into Sales_Online values (3, 'Product501', 'W00000005', 3) ");
        db.execSQL("insert into Sales_Online values (9, 'Product203', 'W00000006', 2) ");
        db.execSQL("insert into Sales_Online values (15, 'Product301', 'W00000007', 10) ");
        db.execSQL("insert into Sales_Online values (4, 'Product402', 'W00000003', 5) ");
        db.execSQL("insert into Sales_Online values (10, 'Product502', 'W00000006', 3) ");
        db.execSQL("insert into Sales_Online values (16, 'Product103', 'W00000003', 1) ");
        db.execSQL("insert into Sales_Online values (5, 'Product403', 'W00000004', 1) ");
        db.execSQL("insert into Sales_Online values (11, 'Product201', 'W00000004', 1) ");
        db.execSQL("insert into Sales_Online values (17, 'Product302', 'W00000008', 5) ");
        db.execSQL("insert into Sales_Online values (6, 'Product101', 'W00000001', 3) ");
        db.execSQL("insert into Sales_Online values (12, 'Product201', 'W00000004', 2) ");
        db.execSQL("insert into Sales_Online values (18, 'Product303', 'W00000001', 5) ");
        Log.d(TAG, "insertDefault: ***end");

    }
}
