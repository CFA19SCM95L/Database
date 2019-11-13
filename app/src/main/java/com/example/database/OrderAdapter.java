package com.example.database;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderHolder> {

    private static final String TAG = "StocksAdapter";
    private List<Order> orderList;
    private OrderListActivity orderAct;

    OrderAdapter(List<Order> orderList, OrderListActivity ma) {
        this.orderList = orderList;
        orderAct = ma;
    }

    @NonNull
    @Override
    public OrderHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: MAKING NEW");
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_order, parent, false);

//        itemView.setOnClickListener(orderAct);
//        itemView.setOnLongClickListener(orderAct);

        return new OrderHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHolder holder, int position) {
        Order order = orderList.get(position);
        holder.orderNumber.setText(order.getOrderNumber());
        holder.quantity.setText(order.getQuantity()+"");
        holder.productName.setText(order.getProductName());
        holder.price.setText(order.getTotalPrice()+"");

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
}
