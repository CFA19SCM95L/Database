package com.example.database;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ShipperHolder extends RecyclerView.ViewHolder {

    public TextView shipperID;
    public TextView shipperName;
    public TextView trackingNumber;

    public ShipperHolder(View view) {
        super(view);
        shipperID = view.findViewById(R.id.shipperID_shipperList);
        shipperName = view.findViewById(R.id.shipperName_shipperList);
        trackingNumber = view.findViewById(R.id.trackingNumber_shipperList);
    }
}
