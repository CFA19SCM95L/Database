package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }


    public void goProfile(View view){
        Intent intent = new Intent(this, ProfileActivity.class);
//        intent.putExtra("username", "value");
        startActivity(intent);
    }
}
