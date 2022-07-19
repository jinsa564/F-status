package com.example.mcarit.enq_enquiry;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.mcarit.enq_enquiry.adapters.UsersRecyclerAdapter;
import com.example.mcarit.enq_enquiry.model.User;
import com.example.mcarit.enq_enquiry.sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

//
//public class UsersList extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_users_list);
//    }
//}

/**
 * Created by lalit on 10/10/2016.
 */

public class UsersListActivity extends AppCompatActivity {

    private AppCompatActivity activity = UsersListActivity.this;
    private TextView textViewFileno,textViewmsg,textViewdate,textViewphone;

    private RecyclerView recyclerViewUsers;
    private List<User> listUsers;
    private UsersRecyclerAdapter usersRecyclerAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        getSupportActionBar().setTitle("F-Status");
        initViews();
        initObjects();

    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        textViewFileno = (TextView) findViewById(R.id.textViewFileno);
        recyclerViewUsers = (RecyclerView) findViewById(R.id.recyclerViewUsers);
        textViewmsg = (TextView) findViewById(R.id.textViewMsg);
        textViewphone = (TextView) findViewById(R.id.textViewPhone);
        textViewdate = (TextView) findViewById(R.id.textViewDate);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        listUsers = new ArrayList<>();
        usersRecyclerAdapter = new UsersRecyclerAdapter(listUsers);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewUsers.setLayoutManager(mLayoutManager);
        recyclerViewUsers.setItemAnimator(new DefaultItemAnimator());
        recyclerViewUsers.setHasFixedSize(true);
        recyclerViewUsers.setAdapter(usersRecyclerAdapter);
        databaseHelper = new DatabaseHelper(activity);

        String filenoFromIntent = getIntent().getStringExtra("fileno");
        String msgFromIntent = getIntent().getStringExtra("reply");
        String phoneFromIntent = getIntent().getStringExtra("phone");
        String dateFromIntent = getIntent().getStringExtra("date");




        textViewFileno.setText(filenoFromIntent);
        textViewmsg.setText(msgFromIntent);
        textViewdate.setText(phoneFromIntent);
        textViewphone.setText(dateFromIntent);

        getDataFromSQLite();
    }

    /**
     * This method is to fetch all user records from SQLite
     */
    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listUsers.clear();
                listUsers.addAll(databaseHelper.getAllUser());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                usersRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
}
