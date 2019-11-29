package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ManufacturerActivity extends AppCompatActivity {

    private final List<Manufacturer> manufacturerList = new ArrayList<>();  // Main content is here

    private RecyclerView recyclerView; // Layout's recyclerview

    private ManufacturerAdapter mAdapter; // Data to recyclerview adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manufacturer);

        Database databaseHandler = new Database(this);
        ArrayList<String[]> manus = databaseHandler.loadManu();

        recyclerView = findViewById(R.id.recycler_manufacturerList);

        mAdapter = new ManufacturerAdapter(manufacturerList, this);

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Make some data - not always needed - used to fill list
        for (String[] manu : manus) {
            manufacturerList.add(new Manufacturer(manu[0], manu[1], manu[2]));
        }

        mAdapter.notifyDataSetChanged();
        //

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
}
