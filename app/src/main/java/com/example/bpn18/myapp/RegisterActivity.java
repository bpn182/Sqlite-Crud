 package com.example.bpn18.myapp;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText name, password, phone, address, email;
    RadioGroup gender;
    Button register, cancel;
    SharedPreferences preferences;
    DatabaseHelper databaseHelper;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        databaseHelper = new DatabaseHelper(this);
        preferences = getSharedPreferences("userinfo", 0);
        id = getIntent().getIntExtra("id", 0);
        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        address = (EditText) findViewById(R.id.address);
        phone = (EditText) findViewById(R.id.phone);
        gender = (RadioGroup) findViewById(R.id.gender);
        register = (Button) findViewById(R.id.register);
        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                password.setText("");
                email.setText("");
                address.setText("");
                phone.setText("");
            }
        });


        ((Button) findViewById(R.id.register)).setText("update");
        if (id != 0) {
            UserInfo info = databaseHelper.getuserList(String.valueOf(id));

            name.setText(info.name);
            email.setText(info.password);
            address.setText(info.address);
            phone.setText(info.phone);
            if (info.gender.equals("Male")) {
                ((RadioButton) findViewById(R.id.male)).setChecked(true);
            } else {
                ((RadioButton) findViewById(R.id.female)).setChecked(true);
            }

        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String namevalue = name.getText().toString();
                String passwordvalue = password.getText().toString();
                String emailvalue = email.getText().toString();
                String phonevalue = phone.getText().toString();
                String addressvalue = address.getText().toString();
                RadioButton checkedBtn = (RadioButton) findViewById(gender.getCheckedRadioButtonId());
                String gendervalue = checkedBtn.getText().toString();

                SharedPreferences.Editor editor = preferences.edit();

                editor.putString("name", namevalue);
                editor.putString("password", passwordvalue);
                editor.putString("email", emailvalue);
                editor.putString("phone", phonevalue);
                editor.putString("address", addressvalue);
                editor.putString("gender", gendervalue);
                editor.apply();

                ContentValues cv = new ContentValues();

                cv.put("name", namevalue);
                cv.put("password", passwordvalue);
                cv.put("email", emailvalue);
                cv.put("address", addressvalue);
                cv.put("phone", phonevalue);
                cv.put("gender", gendervalue);

                if (isValidEmail(emailvalue)) {
                    if (isFieldEmpty(name) && isFieldEmpty(password))
                    if (id == 0) {


                            databaseHelper.insertuser(cv);
                        Toast.makeText(RegisterActivity.this, "user inserted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, loginactivity.class));
                    } else {
                        databaseHelper.updateUser(cv, id + "");
                        Toast.makeText(RegisterActivity.this, "user Updated", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else {
                    email.setError("Enter Valid email address");
                }

            }
        });
    }

    public boolean isFieldEmpty(EditText view){
String value = view.getText().toString();
        if(value.length()>0){
            return true;

        }else{
            view.setError("Enter value");
            return false;
        }
    }
    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }



}



