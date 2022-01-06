package com.example.stgbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.stgbank.Adapters.TransactionAdapter;
import com.example.stgbank.Database.TransactionDatabase;
import com.example.stgbank.Models.Transaction;
import com.example.stgbank.databinding.ActivityAllTransactionsBinding;

import java.util.ArrayList;

public class AllTransactions extends AppCompatActivity {

    ActivityAllTransactionsBinding binding;
    ArrayList<Transaction> transactions;
    TransactionDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllTransactionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("All Transactions");
        database = new TransactionDatabase(this);
        transactions = database.gettransactions();
        if(transactions.size()!=0) binding.notransaction.setVisibility(View.GONE);
        TransactionAdapter adapter = new TransactionAdapter(transactions);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL));
    }
}