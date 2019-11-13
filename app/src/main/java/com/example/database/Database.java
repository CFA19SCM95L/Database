package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Database  extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "BestBuy";
    private static final String TABLE_NAME = "StockWatchTable";
    //attributes
    private static final String SYMBOL = "StockSymbol";
    private static final String COMPANY = "CompanyName";

    private static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + SYMBOL + " TEXT not null unique, " + COMPANY +" TEXT not null)";
    private SQLiteDatabase database;

    public Database(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        database = getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addStock(Product product){
        ContentValues values = new ContentValues();
        values.put(SYMBOL, product.getProductID());
        values.put(COMPANY, product.getProductName());
        values.put(SYMBOL, product.getCategory());
        values.put(COMPANY, product.getPrice());

        database.insert(TABLE_NAME, null, values);
    }

    public void deleteStock(String symbol){
        int cnt = database.delete(TABLE_NAME, SYMBOL +" = ?", new String[] {symbol});
    }

    public ArrayList<String[]> loadStocks(){
        ArrayList<String[]> stocks = new ArrayList<>();
        Cursor cursor = database.query(TABLE_NAME, new String[]{SYMBOL, COMPANY}, null, null, null , null, null);
        if (cursor !=null){
            cursor.moveToFirst();

            for (int i =0; i<cursor.getCount(); i++){
                String symbol = cursor.getString(0);
                String company = cursor.getString(1);
                stocks.add(new String[] {symbol, company});
                cursor.moveToNext();
            }
            cursor.close();
        }
        return stocks;
    }

    /**
     * F: all query
     * F1: addNewOrderToDatabase(productName,quantity,price);
     * F2: updateProductToDatabase(quantity);
     * F3: addNewDataToDatabase(productName,quantity,price,region,season);
     * F4: createUserIntoDatabase(String userName, String password, String cardNumber)
     * F5: editUserToDatabase(String username ,String password, String cardNumber)
     * F5: searchUsersOrder(String username)
     *
     * */

    /**
     * Insert default data to every table
     * */
}
