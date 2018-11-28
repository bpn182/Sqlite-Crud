package com.example.bpn18.myapp;

import android.support.annotation.NonNull;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
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

public class userlistspinnerAdapter  extends ArrayAdapter<UserInfo> {

        Context context;
public userlistspinnerAdapter( Context context, ArrayList<UserInfo>list) {
        super(context, 0 , list);
        this.context = context;
        }
@NonNull
@Override
public View getView(int position, View convertView,ViewGroup parent){

        View view = LayoutInflater.from(context).inflate(R.layout.spinner_item_layout,null);
        TextView text;
final UserInfo info = getItem(position);
        text = (TextView) view.findViewById(R.id.textspinner);
        text.setText(info.name);

        return view;

        }

        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                View view = LayoutInflater.from(context).inflate(R.layout.spinner_item_layout,null);
                TextView text;
                final UserInfo info = getItem(position);
                text = (TextView) view.findViewById(R.id.textspinner);
                text.setText(info.name);
                return view;
        }
}


