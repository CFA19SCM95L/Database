package com.example.database;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * Three table: product, order, salesData
     */

    private final List<Product> productList = new ArrayList<>();

    private RecyclerView recyclerView;

    private ProductAdapter mAdapter;

    private String username;
    private boolean isUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);


        if (getIntent().hasExtra("username")) {
            username = getIntent().getStringExtra("username");
            isUser = true;
        }

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
        final Product m = productList.get(pos);


        if (isUser) {



            /**
             * 1. pop dialog ask user how many they want
             * 2. show price
             * 3. add new order for user
             * 4. update product quantity
             * 5. add one data to sales data
             * 6. go back to profile activity and show their orders
             * */

            //1
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            final EditText et = new EditText(this);
            et.setInputType(InputType.TYPE_CLASS_TEXT);
            et.setGravity(Gravity.CENTER_HORIZONTAL);
            builder.setView(et);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    String productName = m.getProductName();
                    int quantity =Integer.parseInt(et.getText().toString());
                    double price = m.getPrice() * quantity;
                    String region = "online";
                    String season = "Winter";
                    //2
                    Toast.makeText(ProductListActivity.this, "You spend $" + price + " on "+ productName , Toast.LENGTH_SHORT).show();


//                    //3
//                    addNewOrderToDatabase(productName,quantity,price);
//                    //4
//                    updateProductToDatabase(quantity);
//                    //5
//                    addNewDataToDatabase(productName,quantity,price,region,season);


                    //6
                    goProfile();

                }
            });
            builder.setNegativeButton("Change my mind", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Toast.makeText(ProductListActivity.this, "You changed your mind!", Toast.LENGTH_SHORT).show();
                }
            });
            builder.setMessage("How many do you want?");
            builder.setTitle(m.getProductName());
            AlertDialog dialog = builder.create();
            dialog.show();

        }

    }

    public void goProfile() {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }


}
