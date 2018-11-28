package com.example.bpn18.myapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by bpn18 on 10/27/2017.
 */

public class DetailActivity extends AppCompatActivity {
    String  id;
    TextView name,email,address,phone,gender;
    DatabaseHelper databaseHelper;
    ImageView imageview;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);
        id = getIntent().getStringExtra("id");
        databaseHelper = new DatabaseHelper(this);
        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
        address = (TextView) findViewById(R.id.address);
        phone = (TextView) findViewById(R.id.phone);
        gender = (TextView) findViewById(R.id.gender);
        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this,RegisterActivity.class);
                intent.putExtra("id",Integer.parseInt(id));
                startActivity(intent);
            }
        });
        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });

       }
public void showAlertDialog(){
    AlertDialog.Builder dialog = new AlertDialog.Builder(this);
    dialog.setTitle("Delete User");
    dialog.setMessage("Are you sure?");
    dialog.setPositiveButton("ok",new DialogInterface.OnClickListener(){
       @Override
        public void onClick(DialogInterface dialog,int which) {
databaseHelper.deleteUser(id);
           finish();
       }
    });
    dialog.setNegativeButton("cancel",new DialogInterface.OnClickListener(){
        @Override
        public void onClick(DialogInterface dialog,int which) {
        }

    });
    dialog.show();
}
    @Override
    protected void onResume() {
        super.onResume();
        populateData();
    }

    public void populateData()
    {
        UserInfo info= databaseHelper.getuserList(id);

        name.setText(info.name);
        email.setText(info.password);
        address.setText(info.address);
        phone.setText(info.phone);
        gender.setText(info.gender);



    }

}

