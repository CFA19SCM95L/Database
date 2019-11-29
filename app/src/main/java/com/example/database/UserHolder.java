package com.example.database;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class UserHolder extends RecyclerView.ViewHolder {

    public TextView userID;
    public TextView userName;
    public TextView cardNumber;
    public TextView password;

    public UserHolder(View view) {
        super(view);
        userID = view.findViewById(R.id.userID_userList);
        userName = view.findViewById(R.id.userName_userList);
        cardNumber = view.findViewById(R.id.cardNumber_userList);
        password = view.findViewById(R.id.password_userList);
    }
}
