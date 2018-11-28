package com.example.bpn18.myapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bpn18 on 9/1/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    static String name = "bipin1DB";
    static int version = 1;
    String CreateTableuser = "CREATE TABLE IF NOT EXISTS `user` ( `id` INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "`name` TEXT NOT NULL, `password` BLOB NOT NULL," +
            " `email` BLOB, " +
            "`address` TEXT, " +
            "`phone` INTEGER," +
            " `gender` TEXT," +
            " `image` BLOB )";


    public DatabaseHelper(Context context) {
        super(context, name, null, version);
        getWritableDatabase().execSQL(CreateTableuser);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertuser(ContentValues cv) {
        getWritableDatabase().insert("user","", cv);
    }

    public void updateUser(ContentValues cv, String id){
        getWritableDatabase().update("user",cv,"id=?", new String[]{id});
    }

    public void deleteUser(String id){
        getWritableDatabase().delete("user","id=?", new String[]{id});
    }


    public ArrayList<UserInfo> getuserList()

    {
        String sql = "select * from user";
        Cursor c = getWritableDatabase().rawQuery(sql, null);
        ArrayList<UserInfo> list = new ArrayList<UserInfo>();
        while (c.moveToNext()) {
            UserInfo info = new UserInfo();
            info.id = c.getString(c.getColumnIndex("id"));
            info.name = c.getString(c.getColumnIndex("username"));
            info.password = c.getString(c.getColumnIndex("password"));
            info.email = c.getString(c.getColumnIndex("email"));
            info.phone = c.getString(c.getColumnIndex("phone"));
            info.address = c.getString(c.getColumnIndex("address"));
            info.gender = c.getString(c.getColumnIndex("gender"));
            list.add(info);
        }
        c.close();
        return list;

    }

    public UserInfo getuserList(String id)

    {
        String sql = "select * from user where id="+id;
        Cursor c = getWritableDatabase().rawQuery(sql, null);


            UserInfo info = new UserInfo();
        while (c.moveToNext()) {
            info.id = c.getString(c.getColumnIndex("id"));
            info.name = c.getString(c.getColumnIndex("username"));
            info.password = c.getString(c.getColumnIndex("password"));
            info.email = c.getString(c.getColumnIndex("email"));
            info.phone = c.getString(c.getColumnIndex("phone"));
            info.address = c.getString(c.getColumnIndex("address"));
            info.gender = c.getString(c.getColumnIndex("gender"));

        }
        c.close();
        return info;

    }


}
