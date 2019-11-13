package com.example.database;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ManufacturerAdapter extends RecyclerView.Adapter<ManufacturerHolder> {

    private static final String TAG = "ManufacturerAdapter";
    private List<Manufacturer> manufacturerList;
    private ManufacturerActivity mainAct;

    ManufacturerAdapter(List<Manufacturer> manufacturerList, ManufacturerActivity ma) {
        this.manufacturerList = manufacturerList;
        mainAct = ma;
    }

    @NonNull
    @Override
    public ManufacturerHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: MAKING NEW");
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_manufacturer, parent, false);

//        itemView.setOnClickListener(mainAct);
//        itemView.setOnLongClickListener(mainAct);

        return new ManufacturerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ManufacturerHolder holder, int position) {
        Manufacturer manufacturer = manufacturerList.get(position);
        holder.manufacturerID.setText(manufacturer.getManufacturerID());
        holder.companyName.setText(manufacturer.getCompanyName());
        holder.productName.setText(manufacturer.getProduct());

    }

    @Override
    public int getItemCount() {
        return manufacturerList.size();
    }
}
