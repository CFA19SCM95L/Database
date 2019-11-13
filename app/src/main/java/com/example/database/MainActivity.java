package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goUser(View view) {
        Intent intent = new Intent(this, UserLoginActivity.class);
//        intent.putExtra("key", "value");
        startActivity(intent);
    }

    public void goAdmin(View view) {
        Intent intent = new Intent(this, AdminActivity.class);
//        intent.putExtra("key", "value");
        startActivity(intent);
    }
}
