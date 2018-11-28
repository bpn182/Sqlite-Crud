package com.example.bpn18.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;

import java.util.List;

public class userlistviewActivity extends AppCompatActivity {
    ListView listView;
    GridView gridView;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlistview);
        listView = (ListView) findViewById(R.id.listview);
        gridView = (GridView) findViewById(R.id.gridview);
        databaseHelper = new DatabaseHelper(this);
        UserlistAdapter adapter = new UserlistAdapter(this, databaseHelper.getuserList());

        setheaderFooter();
        listView.setAdapter(adapter);
        gridView.setAdapter(adapter);
        boolean show =false;
        if (show) {
            gridView.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);

        } else {

                gridView.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
            }
        }

    public void setheaderFooter() {

View headerview = LayoutInflater.from(this).inflate(R.layout.headerfooter,null);
        listView.addHeaderView(headerview);

        View footerview = LayoutInflater.from(this).inflate(R.layout.footer,null);
        listView.addHeaderView(footerview);
    }
}




