package com.example.database;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class DataHolder extends RecyclerView.ViewHolder {



    public TextView productID;
    public TextView quantity;
    public TextView salesAmount;






    public DataHolder(View view) {
        super(view);
        productID = view.findViewById(R.id.productID_dataList);
        quantity = view.findViewById(R.id.quantity_dataList);
        salesAmount = view.findViewById(R.id.salesAmount_dataList);

    }

}
