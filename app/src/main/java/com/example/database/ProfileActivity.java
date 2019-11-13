package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    public void goEdit(View view) {
        Intent intent = new Intent(this, EditActivity.class);
//        intent.putExtra("key", "value");
        startActivity(intent);
    }

    public void goProduct(View view) {
        Intent intent = new Intent(this, ProductListActivity.class);
//        intent.putExtra("key", "value");
        startActivity(intent);
    }
}
