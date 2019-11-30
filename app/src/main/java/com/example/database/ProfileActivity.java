package com.example.database;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity implements View.OnLongClickListener {


    private final List<Order> orderList = new ArrayList<>();
    private Button editButton;
    private RecyclerView recyclerView;
    private OrderAdapter mAdapter;

    public TextView userName;
    public TextView cardNumber;
    public String username;
    public String cardNum;
    public String memberID;
    public boolean isUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Database db = new Database(this);

        userName = findViewById(R.id.username_profile);
        cardNumber = findViewById(R.id.cardNumber_profile);

        recyclerView = findViewById(R.id.recyclerView_order);

        mAdapter = new OrderAdapter(orderList, this);

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        if (getIntent().hasExtra("username")) {
            username = getIntent().getStringExtra("username");
            String[] data = db.getUserProfile(username);

            userName.setText(data[1]);
            cardNumber.setText(data[2]);
            memberID = data[0];
            ArrayList<String[]> orders = db.loadUsrOrder(memberID);

            int i = 1;
            for (String[] order : orders) {
                Order order1 = new Order(i+"", order[1], Integer.parseInt(order[2]), Double.parseDouble(order[3]));
                order1.realOrderNumber = order[0];
                orderList.add(order1);
                i++;
//                orderList.add(new Order(order[0], order[1], Integer.parseInt(order[2]), Double.parseDouble(order[3])));

            }
            isUser = true;
        } else {
            editButton = findViewById(R.id.button3);
            editButton.setClickable(false);
            editButton.setVisibility(View.INVISIBLE);

        }

        mAdapter.notifyDataSetChanged();
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

    }

    public void goEdit(View view) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("memberID", memberID);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    public void goProduct(View view) {
        Intent intent = new Intent(this, ProductListActivity.class);
        intent.putExtra("memberID", memberID);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.goAdmin:
                Intent intent = new Intent(this, AdminActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onLongClick(View v) {  // long click listener called by ViewHolder long clicks
        int pos = recyclerView.getChildLayoutPosition(v);
        final Order order = orderList.get(pos);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                deleteOrder(order.realOrderNumber);
                makeToast(0);
                orderList.remove(order);
                mAdapter.notifyDataSetChanged();

            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                makeToast(1);
            }
        });
        builder.setMessage("Do you want to delete" + order.getOrderNumber());
        builder.setTitle("Delete order");
        AlertDialog dialog = builder.create();
        dialog.show();


        return false;
    }

    private void makeToast(int i) {
        if (i == 1) {
            Toast.makeText(this, "You change your mind", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Successfully delete order", Toast.LENGTH_LONG).show();
        }
    }

    private void deleteOrder(String orderID) {
        Database db = new Database(this);
        db.deleteUserOrder(orderID);
    }
}
