package com.example.bpn18.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity {

    LinearLayout container;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        container = (LinearLayout) findViewById(R.id.container);
        databaseHelper = new DatabaseHelper(this);
populateData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateData();
    }

    public void populateData() {
        ArrayList<UserInfo> list = databaseHelper.getuserList();
container.removeAllViews();
        for (final UserInfo info : list
                ) {
      //      TextView textview = new TextView(this);
       //     textview.setText("Name:" + info.username + "\n" + "Address:" + info.address);
       //     container.addView(textview);

            View view = LayoutInflater.from(this).inflate(R.layout.item_layout,null);
            TextView username, address;
            username = (TextView) view.findViewById(R.id.username);
            address = (TextView) view.findViewById(R.id.address);
            username.setText("Name: " +info.name);
            address.setText("Address: "+ info.address);

            view.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(UserListActivity.this,DetailActivity.class);
                    intent.putExtra("id", info.id);
                    startActivity(intent);
                }

            });

            container.addView(view);
        }
    }
}


