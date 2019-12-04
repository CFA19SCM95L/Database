package com.example.database;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UserlistActivity extends AppCompatActivity implements View.OnLongClickListener {


    private final List<User> userList = new ArrayList<>();
    private RecyclerView recyclerView;
    private UserAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);

        Database databaseHandler = new Database(this);
        ArrayList<String[]> users = databaseHandler.loadUser();

        recyclerView = findViewById(R.id.recycler_userlist);
        mAdapter = new UserAdapter(userList, this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        for (String[] user : users) {
            userList.add(new User(user[0], user[1], user[3], user[2]));
        }

        mAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onLongClick(View v) {  // long click listener called by ViewHolder long clicks
        int pos = recyclerView.getChildLayoutPosition(v);
        final User user = userList.get(pos);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (user.getAccountNumber().equals("guest")) {
                    makeToast(3);
                    return;
                }


                deleteUser(user.getUserID());
                makeToast(2);
                userList.remove(user);
                mAdapter.notifyDataSetChanged();

            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                makeToast(1);
            }
        });
        builder.setMessage("Do you want to delete" + user.getUserID());
        builder.setTitle("Delete user");
        AlertDialog dialog = builder.create();
        dialog.show();
        return false;
    }

    private void makeToast(int i) {
        if (i == 1) {
            Toast.makeText(this, "You change your mind", Toast.LENGTH_LONG).show();
        } else if (i == 2) {
            Toast.makeText(this, "Successfully delete user", Toast.LENGTH_LONG).show();
        } else if (i == 3) {
            Toast.makeText(this, "Cannot delete guest account", Toast.LENGTH_LONG).show();
        }
    }

    private void deleteUser(String userID) {
        Database db = new Database(this);
        db.deleteUser(userID);
    }

}
