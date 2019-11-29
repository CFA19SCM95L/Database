package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class DataActivity extends AppCompatActivity {


    private final List<Data> dataList = new ArrayList<>();

    private RecyclerView recyclerView;

    private DataAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        Database databaseHandler  = new Database(this);
        ArrayList<String[]> datas = databaseHandler.loadSalesData();


        recyclerView = findViewById(R.id.recycler_dataList);

        mAdapter = new DataAdapter(dataList, this);

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        for (String[] data : datas) {
            dataList.add(new Data(data[0],Integer.parseInt(data[1]), Double.parseDouble(data[2])));
        }

        mAdapter.notifyDataSetChanged();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
}
