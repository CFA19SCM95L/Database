package com.example.database;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter  extends RecyclerView.Adapter<ProductHolder> {

    private static final String TAG = "ProductAdapter";
    private List<Product> productList;
    private ProductListActivity productAct;

    ProductAdapter(List<Product> productList, ProductListActivity ma) {
        this.productList = productList;
        productAct = ma;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: MAKING NEW");
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_product, parent, false);

        itemView.setOnClickListener(productAct);

        return new ProductHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        Product product = productList.get(position);
        holder.productID.setText(product.getProductID());
        holder.productName.setText(product.getProductName());
        holder.category.setText(product.getCategory());
        holder.price.setText(product.getPrice()+"");
        holder.quantity.setText(product.getQuantity()+"");
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
