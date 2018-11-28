package com.example.bpn18.myapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class loginactivity extends AppCompatActivity {

    EditText uname , password ;
    TextView result;
    Button Lbotton,Register;
    SharedPreferences preferences;
    Spinner spinner;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        spinner = (Spinner) findViewById(R.id.spinner);
        preferences=getSharedPreferences("userinfo",0);
        databaseHelper = new DatabaseHelper(this);
        uname= (EditText) findViewById(R.id.uname);
        password=(EditText)findViewById(R.id.password);
        result= (TextView)findViewById(R.id.result);
        Register=(Button)findViewById(R.id.Register);
        spinner.setAdapter(new userlistspinnerAdapter(this,databaseHelper.getuserList()));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                UserInfo info = (UserInfo)spinner.getSelectedItem();
                uname.setText(info.name);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Lbotton=(Button) findViewById((R.id.Lbotton));
        Lbotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("tag", "value:" + uname);
                result.setText(uname.getText().toString());
                UserInfo info = (UserInfo)spinner.getSelectedItem();
               String str = info.name;

                String registeredusername = preferences.getString("username", "");
                String registeredpassword = preferences.getString("password", "");

                if (registeredusername.equals(uname.getText().toString()) && registeredpassword.equals(password.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Welcome" + uname.getText().toString(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(loginactivity.this, RegisterActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(loginactivity.this, "login failure", Toast.LENGTH_SHORT).show();
                }
                startActivity(new Intent(loginactivity.this,UserListActivity.class));


            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginactivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });






    }

}
