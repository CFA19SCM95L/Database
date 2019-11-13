package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    /**
     * Two table: User, Order
     * */

    private final List<Order> orderList = new ArrayList<>();  // Main content is here

    private RecyclerView recyclerView; // Layout's recyclerview

    private OrderAdapter mAdapter; // Data to recyclerview adapter

    public TextView userName;
    public TextView cardNumber;

    public String username;
    public String password;
    public String cardNum;

    public boolean isUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        userName = findViewById(R.id.username_profile);
        cardNumber = findViewById(R.id.cardNumber_profile);


        if (getIntent().hasExtra("username")) {
            username = getIntent().getStringExtra("username");
            password = getIntent().getStringExtra("password");
            userName.setText(username);
            isUser = true;
        }

        if (getIntent().hasExtra("cardNumber")) {
            cardNum = getIntent().getStringExtra("cardNumber");
            cardNumber.setText(cardNum);
        }


        recyclerView = findViewById(R.id.recyclerView_order);

        mAdapter = new OrderAdapter(orderList, this);

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Make some data - not always needed - used to fill list
        for (int i = 0; i < 20; i++) {
            orderList.add(new Order());
        }
        mAdapter.notifyDataSetChanged();

        //

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

    }

    public void goEdit(View view) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("userName", username);
        startActivity(intent);
    }

    public void goProduct(View view) {
        Intent intent = new Intent(this, ProductListActivity.class);
        intent.putExtra("isUser", isUser);
        startActivity(intent);
    }
}
