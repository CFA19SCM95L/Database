package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ShipperActivity extends AppCompatActivity {

    private final List<Shipper> shipperList = new ArrayList<>();

    private RecyclerView recyclerView;

    private ShipperAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipper);

        recyclerView = findViewById(R.id.recycler_shipper);

        mAdapter = new ShipperAdapter(shipperList, this);

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        //Make some data
        for (int i = 0; i < 20; i++) {
            shipperList.add(new Shipper());
        }
        mAdapter.notifyDataSetChanged();
        //
    }
}
