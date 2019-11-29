package com.example.database;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class OrderHolder extends RecyclerView.ViewHolder {


    public TextView orderNumber;
    public TextView quantity;
    public TextView productName;
    public TextView price;

    public OrderHolder(View view) {
        super(view);
        orderNumber = view.findViewById(R.id.orderID_orderList);
        productName = view.findViewById(R.id.productName_orderList);
        price = view.findViewById(R.id.totalPrice_orderList);
        quantity = view.findViewById(R.id.quantity_orderList);

    }
}
