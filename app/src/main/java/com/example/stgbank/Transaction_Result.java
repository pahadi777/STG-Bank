package com.example.stgbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.stgbank.databinding.ActivityTransactionResultBinding;

public class Transaction_Result extends AppCompatActivity {

    ActivityTransactionResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTransactionResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        double sbalance = getIntent().getDoubleExtra("sbalance",0);
        if(sbalance<0)
        {
            binding.animationView.setAnimation(R.raw.orderfail);
            binding.status.setTextColor(getResources().getColor(R.color.red));
            binding.status.setText("TRANSACTION FAILED DUE TO INSUFFICIENT BALANCE ");
        }
        getSupportActionBar().hide();
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally
                {
                    Intent intent = new Intent(getApplicationContext(),All_Users.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        };
        thread.start();
    }
}