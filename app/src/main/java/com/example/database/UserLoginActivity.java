package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UserLoginActivity extends AppCompatActivity {

    public EditText username;
    public EditText password;
    public String usernamePass;
    public String passwordPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        username = findViewById(R.id.userName_Login);
        password = findViewById(R.id.password_login);

    }

    public void goProfile(View view){
        usernamePass = username.getText().toString();
        passwordPass = password.getText().toString();
        if(isAuth(usernamePass,passwordPass)) {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("username", usernamePass);
            intent.putExtra("password", passwordPass);
            startActivity(intent);
        } else {
            Toast.makeText(this,"Wrong credential", Toast.LENGTH_SHORT).show();
        }
    }


    public void goSignUp(View view){
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    public boolean isAuth(String userName, String password){
        Database db = new Database(this);
        return db.checkUser(userName,password);
    }

    public void guestLogin(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("username", "guest");
        intent.putExtra("password", "guest");
        startActivity(intent);

    }
}
