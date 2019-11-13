package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.ProxyFileDescriptorCallback;
import android.view.View;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }


    public void goProductList(View view) {
        Intent intent = new Intent(this, ProductListActivity.class);
//        intent.putExtra("key", "value");
        startActivity(intent);
    }
    public void goOrderList(View view) {
        Intent intent = new Intent(this, OrderListActivity.class);
//        intent.putExtra("key", "value");
        startActivity(intent);
    }
    public void goUserList(View view) {
        Intent intent = new Intent(this, UserlistActivity.class);
//        intent.putExtra("key", "value");
        startActivity(intent);
    }
    public void goShipperList(View view) {
        Intent intent = new Intent(this, ShipperActivity.class);
//        intent.putExtra("key", "value");
        startActivity(intent);
    }
    public void goDataList(View view) {
        Intent intent = new Intent(this, DataActivity.class);
//        intent.putExtra("key", "value");
        startActivity(intent);
    }
    public void goManufacturerList(View view) {
        Intent intent = new Intent(this, Manufacturer.class);
//        intent.putExtra("key", "value");
        startActivity(intent);
    }
}
