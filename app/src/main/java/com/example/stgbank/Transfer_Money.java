package com.example.stgbank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.stgbank.Fragments.Amount;
import com.example.stgbank.Models.User;
import com.example.stgbank.databinding.ActivityTransferMoneyBinding;

public class Transfer_Money extends AppCompatActivity {

    ActivityTransferMoneyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTransferMoneyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("User Profile");
        User user = getIntent().getParcelableExtra("user");
        binding.entername.setText(user.getName());
        binding.entermail.setText(user.getMail());
        binding.enternumber.setText(user.getPhone());
        binding.balance.setText(String.valueOf(user.getBalance()));

        binding.button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Amount amount = new Amount();
                Bundle bundle = new Bundle();
                bundle.putParcelable("user",user);
//                bundle.putParcelable();/
                amount.setArguments(bundle);
                amount.show(getSupportFragmentManager(),amount.getTag());
            }
        });
    }
}