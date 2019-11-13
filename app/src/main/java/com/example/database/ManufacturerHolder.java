package com.example.database;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ManufacturerHolder extends RecyclerView.ViewHolder {

    public TextView manufacturerID;
    public TextView companyName;
    public TextView productName;





    public ManufacturerHolder(View view) {
        super(view);
        manufacturerID = view.findViewById(R.id.manufacturerID);
        companyName = view.findViewById(R.id.manufacturerName);
        productName = view.findViewById(R.id.productName_manufacturer);

    }
}
