package com.example.database;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataHolder> {

    private static final String TAG = "DataAdapter";
    private List<Data> dataList;
    private DataActivity dataActivity;

    DataAdapter(List<Data> dataList, DataActivity ma) {
        this.dataList = dataList;
        dataActivity = ma;
    }

    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: MAKING NEW");
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_data, parent, false);

//        itemView.setOnClickListener(dataActivity);
//        itemView.setOnLongClickListener(dataActivity);

        return new DataHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DataHolder holder, int position) {
        Data data = dataList.get(position);
        holder.region.setText(data.getRegion());
        holder.season.setText(data.getSeason());
        holder.productID.setText(data.getProductID());
        holder.quantity.setText(data.getQuantity()+"");
        holder.salesAmount.setText(data.getSalesAmount()+"");

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
