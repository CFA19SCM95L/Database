package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public Database databaseHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        databaseHandler  = new Database(this);
        databaseHandler.countAll();
        databaseHandler.loadUser();
        databaseHandler.loadProduct();
        databaseHandler.loadOrder();
    }

    public void goUser(View view) {
        Intent intent = new Intent(this, UserLoginActivity.class);
        startActivity(intent);
    }

    public void goAdmin(View view) {
        Intent intent = new Intent(this, AdminActivity.class);
        startActivity(intent);
    }
}
