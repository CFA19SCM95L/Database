package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private final List<Order> orderList = new ArrayList<>();  // Main content is here

    private RecyclerView recyclerView; // Layout's recyclerview

    private OrderAdapter mAdapter; // Data to recyclerview adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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
//        intent.putExtra("key", "value");
        startActivity(intent);
    }

    public void goProduct(View view) {
        Intent intent = new Intent(this, ProductListActivity.class);
//        intent.putExtra("key", "value");
        startActivity(intent);
    }
}
