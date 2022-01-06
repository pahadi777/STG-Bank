package com.example.stgbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.stgbank.Adapters.AllUserAdapter;
import com.example.stgbank.Database.TransactionDatabase;
import com.example.stgbank.Database.UserDatabase;
import com.example.stgbank.Models.Transaction;
import com.example.stgbank.Models.User;
import com.example.stgbank.databinding.ActivityAllUsersBinding;

import java.util.ArrayList;
import java.util.List;

public class All_Users extends AppCompatActivity {

    ActivityAllUsersBinding binding;
    UserDatabase database;
    ArrayList<User> users;
    User user;
    int code;
    Double amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllUsersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        code = intent.getIntExtra("code",0);
        if(code==1)
        {
            user = intent.getParcelableExtra("user");
            getSupportActionBar().setTitle("Send To");
            amount = intent.getDoubleExtra("amount",0);
        }
        else getSupportActionBar().setTitle("All Users");
        getusers();
        AllUserAdapter adapter = new AllUserAdapter(users);
        adapter.setonitemclicklistener(new AllUserAdapter.Clicklistener() {
            @Override
            public void onitemclick(int position, View v)
            {
                if(code==0)
                {
                    Intent intent = new Intent(All_Users.this,Transfer_Money.class);
                    intent.putExtra("user", users.get(position));
                    startActivity(intent);
                }
                else
                {
                    String sname = user.getName();
                    User receiver = users.get(position);
                    String message = sname + " !! Are You Sure You Want To Send ₹ " + amount + " To " + receiver.getName() + " . If You Click Yes You Can't Reverse Your Transaction";
                    new AlertDialog.Builder(All_Users.this).setIcon(R.drawable.moneytransfer).setTitle("Confirmation Alert !!!").setMessage(message).setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i)
                        {
                            double sbalance = user.getBalance()-amount;
                            double rbalance = receiver.getBalance()+amount;
                            Intent intent = new Intent(getApplicationContext(),Transaction_Result.class);
                            intent.putExtra("sbalance",sbalance);
                            TransactionDatabase tdatabase = new TransactionDatabase(getApplicationContext());
                            if(sbalance>=0)
                            {
                                database.updatebalance(user,sbalance);
                                database.updatebalance(receiver,rbalance);
                                tdatabase.inserttransactions(new Transaction(amount,"Success",user.getName(),receiver.getName()));
                            }
                            else
                                tdatabase.inserttransactions(new Transaction(amount,"Failed",user.getName(),receiver.getName()));

                            ProgressDialog progress = new ProgressDialog(All_Users.this);
                            progress.setTitle("Please Wait ...");
                            progress.setMessage("Your transaction is being processed");
                            progress.show();

                            Thread thread = new Thread(){
                                @Override
                                public void run() {
                                    try {
                                        sleep(3000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    finally {
                                        progress.dismiss();
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            };
                            thread.start();
                        }
                    }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i)
                        {
                            dialogInterface.dismiss();
                        }
                    }).show();
                }
            }
        });
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }

    void getusers()
    {
        database = new UserDatabase(this);
        users = database.getusers(user);
        if(users.size()==0)
        {
            database.insertusers(1, "Kamal", "+91 8652398456", (double) 8000, "kamal@gmail.com");
            database.insertusers(2, "Joshi", "+91 8394023254", (double) 6700, "joshi@gmail.com");
            database.insertusers(3, "Harshit", "+91 9235672189", (double) 9200, "harshit@gmail.com");
            database.insertusers(4, "Guddu", "+91 9276349871", (double) 15000, "guddu@gmail.com");
            database.insertusers(5, "Anmol", "+91 9123487349", (double) 6800, "anmol@gmail.com");
            database.insertusers(6, "Gani", "+91 9275642903", (double) 5200, "gani@gmail.com");
            database.insertusers(7, "Sita", "+91 9036518556", (double) 23900, "sita@gmail.com");
            database.insertusers(8, "Ram", "+91 9126738492", (double) 90000, "ram@gmail.com");
            database.insertusers(9, "Karan", "+91 8453761290", (double) 60045, "ram@gmail.com");
            database.insertusers(10, "George", "+1 570-513-3821", (double) 25000, "george@gmail.com");
            users = database.getusers(user);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId()==R.id.viewall)
        {
            startActivity(new Intent(getApplicationContext(), AllTransactions.class));
        }
        return super.onOptionsItemSelected(item);
    }

}