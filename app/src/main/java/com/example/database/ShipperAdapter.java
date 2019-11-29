package com.example.database;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShipperAdapter  extends RecyclerView.Adapter<ShipperHolder> {

    private static final String TAG = "ShipperAdapter";
    private List<Shipper> shipperList;
    private ShipperActivity shipperAct;

    ShipperAdapter(List<Shipper> shipperList, ShipperActivity ma) {
        this.shipperList = shipperList;
        shipperAct = ma;
    }

    @NonNull
    @Override
    public ShipperHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: MAKING NEW");
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_shipper, parent, false);
        return new ShipperHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ShipperHolder holder, int position) {
        Shipper shipper = shipperList.get(position);
        holder.shipperID.setText(shipper.getShipperID());
        holder.shipperName.setText(shipper.getShipperName());
        holder.trackingNumber.setText(shipper.getTrackingNumber());
    }

    @Override
    public int getItemCount() {
        return shipperList.size();
    }

}
