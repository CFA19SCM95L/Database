package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }


    public void goProductList(View view) {
        Intent intent = new Intent(this, ProductListActivity.class);
        startActivity(intent);
    }
    public void goOrderList(View view) {
        Intent intent = new Intent(this, OrderListActivity.class);
        startActivity(intent);
    }
    public void goUserList(View view) {
        Intent intent = new Intent(this, UserlistActivity.class);
        startActivity(intent);
    }
    public void goShipperList(View view) {
        Intent intent = new Intent(this, ShipperActivity.class);
        startActivity(intent);
    }
    public void goDataList(View view) {
        Intent intent = new Intent(this, DataActivity.class);
        startActivity(intent);
    }
    public void goManufacturerList(View view) {
        Intent intent = new Intent(this, ManufacturerActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.goLogin:
                Intent intent = new Intent(this, UserLoginActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
