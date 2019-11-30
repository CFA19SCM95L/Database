package com.example.database;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserHolder> {

    private static final String TAG = "UserAdapter";
    private List<User> userList;
    private UserlistActivity userListAct;

    UserAdapter(List<User> userList, UserlistActivity ma) {
        this.userList = userList;
        userListAct = ma;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: MAKING NEW");
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_user, parent, false);
        itemView.setOnLongClickListener(userListAct);

        return new UserHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        User user = userList.get(position);
        holder.userID.setText(user.getUserID());
        holder.userName.setText(user.getAccountNumber());
        holder.cardNumber.setText(user.getCardNumber());
        holder.password.setText(user.getPassword());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
