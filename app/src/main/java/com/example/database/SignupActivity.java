package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    public EditText usernameEdit;
    public EditText passwordEdit;
    public EditText cardNumberEdit;
    public String userName;
    public String password;
    public String cardNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        usernameEdit = findViewById(R.id.username_signup);
        passwordEdit = findViewById(R.id.password_signup);
        cardNumberEdit = findViewById(R.id.cardNumber_signup);
    }


    public void goProfile(View view) {
        userName = usernameEdit.getText().toString();
        password = passwordEdit.getText().toString();
        cardNumber = cardNumberEdit.getText().toString();

        if (cardNumber.length() == 16 && userName.length() > 0 && password.length() > 0) {
            createUserIntoDatabase(userName, password, cardNumber);
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("username", userName);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Wrong Input: Card number need to be 16 digits", Toast.LENGTH_SHORT).show();
        }

    }

    public void createUserIntoDatabase(String userName, String password, String cardNumber) {
        Database db = new Database(this);
        db.addUser(userName, password, cardNumber);

    }
}
