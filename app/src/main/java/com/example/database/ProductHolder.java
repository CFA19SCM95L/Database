package com.example.database;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ProductHolder extends RecyclerView.ViewHolder {


    public TextView productID;
    public TextView productName;
    public TextView category;
    public TextView price;
    public TextView quantity;





    public ProductHolder(View view) {
        super(view);
        productID = view.findViewById(R.id.productID_productList);
        productName = view.findViewById(R.id.productName_productList);
        category = view.findViewById(R.id.category_productList);
        price = view.findViewById(R.id.productPrice_productList);
        quantity = view.findViewById(R.id.productQuantity_productList);

    }
}
