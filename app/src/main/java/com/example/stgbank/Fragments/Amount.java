package com.example.stgbank.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.airbnb.lottie.utils.Utils;
import com.example.stgbank.All_Users;
import com.example.stgbank.Models.User;
import com.example.stgbank.R;
import com.example.stgbank.databinding.FragmentAmountBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class Amount extends BottomSheetDialogFragment {

    public Amount() { }

    FragmentAmountBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAmountBinding.inflate(inflater, container, false);
        User user = this.getArguments().getParcelable("user");
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String string = binding.enteramount.getText().toString();
                try
                {
                    Double amount = Double.valueOf(string);
                    Intent intent = new Intent(getContext(), All_Users.class);
                    intent.putExtra("user",user);
                    intent.putExtra("code",1);
                    intent.putExtra("amount",amount);
                    startActivity(intent);
                }
                catch (Exception e)
                {
                    binding.enteramount.setError("Enter a valid amount");
                }
            }
        });
        return binding.getRoot();
    }
}