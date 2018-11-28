package com.example.bpn18.myapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

/**
 * Created by bpn18 on 10/28/2017.
 */

public class UserlistAdapter extends ArrayAdapter<UserInfo> {

Context context;
    public UserlistAdapter( Context context, ArrayList<UserInfo>list) {
        super(context, 0 , list);
        this.context = context;
    }
    @NonNull
@Override
public View getView(int position, View convertView,ViewGroup parent){

        View view = LayoutInflater.from(context).inflate(R.layout.item_layout,null);
        TextView username, address;
        final UserInfo info = getItem(position);
        username = (TextView) view.findViewById(R.id.username);
        address = (TextView) view.findViewById(R.id.address);
        username.setText("Name: " +info.name);
        address.setText("Address: "+ info.address);

        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context,DetailActivity.class);
                intent.putExtra("id", info.id);
               context.startActivity(intent);
            }

        });
        return view;
    }
}
