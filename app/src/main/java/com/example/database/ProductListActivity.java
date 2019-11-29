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


    private final List<Product> productList = new ArrayList<>();

    private RecyclerView recyclerView;

    private ProductAdapter mAdapter;

    private boolean isUser;
    public String memberID;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        Database databaseHandler  = new Database(this);
        ArrayList<String[]> products =databaseHandler.loadProduct();


        if (getIntent().hasExtra("memberID")) {
            memberID = getIntent().getStringExtra("memberID");
            username = getIntent().getStringExtra("username");
            isUser = true;
        }

        recyclerView = findViewById(R.id.recyclerView_productList);
        mAdapter = new ProductAdapter(productList, this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        for (String[] product : products) {
            productList.add(new Product(product[0],product[1],product[2],Integer.parseInt(product[3]),Double.parseDouble(product[4])));
        }

        mAdapter.notifyDataSetChanged();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onClick(View v) {
        int pos = recyclerView.getChildLayoutPosition(v);
        final Product m = productList.get(pos);

        if (isUser) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            final EditText et = new EditText(this);
            et.setInputType(InputType.TYPE_CLASS_TEXT);
            et.setGravity(Gravity.CENTER_HORIZONTAL);
            builder.setView(et);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    String productName = m.getProductName();
                    int quantity =Integer.parseInt(et.getText().toString());
                    if (quantity <= m.getQuantity() ) {
                        double price = m.getPrice() * quantity;
                        Toast.makeText(ProductListActivity.this, "You spend $" + price + " on "+ productName , Toast.LENGTH_SHORT).show();
                        userSelect( m.getProductID(), quantity);
                        goProfile();
                    } else {
                        Toast.makeText(ProductListActivity.this, "Exceed total quantity" , Toast.LENGTH_SHORT).show();

                    }

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

    public void  userSelect(String productID, int quantity) {
        Database database = new Database(this);
        database.userbuy(memberID, productID, quantity);
    }

    public void goProfile() {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

}
