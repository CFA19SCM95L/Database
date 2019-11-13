package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity implements View.OnClickListener {

    private final List<Product> productList = new ArrayList<>();

    private RecyclerView recyclerView;

    private ProductAdapter mAdapter;

    private boolean isUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        recyclerView = findViewById(R.id.recyclerView_productList);

        mAdapter = new ProductAdapter(productList, this);

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Make some data
        for (int i = 0; i < 20; i++) {
            productList.add(new Product());
        }
        mAdapter.notifyDataSetChanged();
        //


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onClick(View v) {  // click listener called by ViewHolder clicks

        int pos = recyclerView.getChildLayoutPosition(v);
        Product m = productList.get(pos);



//        if(isUser) {
//            Toast.makeText(v.getContext(), "User " + m.toString(), Toast.LENGTH_SHORT).show();
//        }
        Toast.makeText(v.getContext(), "User " + m.toString(), Toast.LENGTH_SHORT).show();


    }
}
