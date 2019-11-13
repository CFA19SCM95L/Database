package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {

    public TextView userName;
    public EditText passwordEdit;
    public EditText cardNumberEdit;
    public String username;
    public String password;
    public String cardNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        userName = findViewById(R.id.userName_edit);
        passwordEdit = findViewById(R.id.password_edit);
        cardNumberEdit = findViewById(R.id.cardNumber_edit);

        if (getIntent().hasExtra("userName")) {
            username = getIntent().getStringExtra("userName");
            userName.setText(username);
        }

    }

    public void goProfile(View view) {
        password = passwordEdit.getText().toString();
        cardNumber = cardNumberEdit.getText().toString();
        editUserToDatabase(username,password,cardNumber);
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("cardNumber", cardNumber);

        startActivity(intent);
    }
    public void editUserToDatabase(String username ,String password, String cardNumber) {
        /**
         * pass userName to find user and modify password and cardNUmber
         * **/
    }
}
