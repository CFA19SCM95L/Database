package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {


    private final List<Order> orderList = new ArrayList<>();
    private Button editButton;
    private RecyclerView recyclerView;
    private OrderAdapter mAdapter;

    public TextView userName;
    public TextView cardNumber;
    public String username;
    public String cardNum;
    public String memberID;
    public boolean isUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Database db = new Database(this);

        userName = findViewById(R.id.username_profile);
        cardNumber = findViewById(R.id.cardNumber_profile);

        recyclerView = findViewById(R.id.recyclerView_order);

        mAdapter = new OrderAdapter(orderList, this);

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        if (getIntent().hasExtra("username")) {
            username = getIntent().getStringExtra("username");
            String[] data = db.getUserProfile(username);

            userName.setText(data[1]);
            cardNumber.setText(data[2]);
            memberID = data[0];
            ArrayList<String[]> orders= db.loadUsrOrder(memberID);

            int i = 1;
            for (String[] order : orders) {
                orderList.add(new Order(i+"", order[1], Integer.parseInt(order[2]), Double.parseDouble(order[3])));
                i++;
            }
            isUser = true;
        } else {
            editButton = findViewById(R.id.button3);
            editButton.setClickable(false);
            editButton.setVisibility(View.INVISIBLE);

        }

        mAdapter.notifyDataSetChanged();
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

    }

    public void goEdit(View view) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("memberID", memberID);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    public void goProduct(View view) {
        Intent intent = new Intent(this, ProductListActivity.class);
        intent.putExtra("memberID", memberID);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.goAdmin:
                Intent intent = new Intent(this, AdminActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
