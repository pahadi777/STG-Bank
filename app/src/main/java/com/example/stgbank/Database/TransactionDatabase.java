package com.example.stgbank.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.stgbank.Models.Transaction;
import com.example.stgbank.Models.User;

import java.util.ArrayList;

public class TransactionDatabase extends SQLiteOpenHelper
{

    final static String DBNAME = "transactiondatabase.db";
    final static int DBVERSION = 1;

    public TransactionDatabase(@Nullable Context context) {
        super(context,DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL("create table transactions " + "(id integer primary key autoincrement ," + "amount real," + "status text," + "sname text," + "rname text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("Drop table if exists transactions");
        onCreate(sqLiteDatabase);
    }

    public ArrayList<Transaction> gettransactions ()
    {
        ArrayList<Transaction> mytransactions = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from transactions",null);

        if(cursor.moveToLast())
        {
            Transaction t = new Transaction(cursor.getInt(0),cursor.getDouble(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
            mytransactions.add(t);

            while (cursor.moveToPrevious())
            {
                Transaction transaction = new Transaction(cursor.getInt(0),cursor.getDouble(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
                mytransactions.add(transaction);
            }
        }
        cursor.close();
        database.close();
        return mytransactions;
    }

    public boolean inserttransactions (Transaction transaction)
    {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("amount",transaction.getAmount());
        values.put("status",transaction.getStatus());
        values.put("sname",transaction.getSname());
        values.put("rname",transaction.getRname());
        long temp = database.insert("transactions",null,values);
        database.close();
        return temp > 0;
    }

}
