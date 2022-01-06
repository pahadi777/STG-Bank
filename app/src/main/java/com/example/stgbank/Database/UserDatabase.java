package com.example.stgbank.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.stgbank.Models.User;

import java.util.ArrayList;

public class UserDatabase extends SQLiteOpenHelper
{
    final static String DBNAME = "userdatabase.db";
    final static int DBVERSION = 1;

    public UserDatabase(@Nullable Context context)
    {
        super(context,DBNAME,null,DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL("create table users " + "(id integer primary key ," + "name text," + "phone text," + "balance real," + "mail text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("Drop table if exists users");
        onCreate(sqLiteDatabase);
    }

    public boolean insertusers(int id , String name , String phone , Double balance , String mail)
    {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id",id);
        values.put("name",name);
        values.put("phone",phone);
        values.put("balance",balance);
        values.put("mail",mail);
        long temp = database.insert("users",null,values);
        database.close();
        return temp > 0;
    }

    public ArrayList<User> getusers (User myuser)
    {
        ArrayList<User> myorders = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from users",null);
        if(cursor.moveToFirst())
        {
            User user = new User(cursor.getString(1),cursor.getString(2),cursor.getString(4),cursor.getDouble(3),cursor.getInt(0));
            if(myuser==null || myuser.getId()!=user.getId()) myorders.add(user);
            while (cursor.moveToNext()) {
                User newuser = new User(cursor.getString(1),cursor.getString(2),cursor.getString(4),cursor.getDouble(3),cursor.getInt(0));
                if(myuser==null || myuser.getId()!=newuser.getId()) myorders.add(newuser);
            }
        }
        cursor.close();
        database.close();
        return myorders;
    }

    public boolean updatebalance(User user , Double balance)
    {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",user.getName());
        values.put("phone",user.getPhone());
        values.put("balance",balance);
        values.put("mail",user.getMail());
        long row = database.update("users",values,"id="+user.getId(),null);
        database.close();
        return row > 0;
    }

}
