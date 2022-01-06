package com.example.stgbank.Adapters;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stgbank.Models.Transaction;
import com.example.stgbank.R;
import com.example.stgbank.databinding.TransactionAdapterBinding;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.viewholder>
{

    ArrayList<Transaction> list;

    public TransactionAdapter(ArrayList<Transaction> list)
    {
        this.list = list;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        TransactionAdapterBinding binding = TransactionAdapterBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position)
    {
        Transaction transaction = list.get(position);
        holder.binding.id.setText(transaction.getId()+"");
        holder.binding.amount.setText(String.valueOf(transaction.getAmount()));
        holder.binding.sname.setText(transaction.getSname());
        holder.binding.rname.setText(transaction.getRname());
        String status = transaction.getStatus();
        if(status.equals("Success"))
        {
            holder.binding.statusimage.setImageResource(R.drawable.success);
            holder.binding.status.setText(status);
            holder.binding.status.setTextColor(holder.binding.getRoot().getResources().getColor(R.color.green));
        }
        else
        {
            holder.binding.statusimage.setImageResource(R.drawable.failed);
            holder.binding.status.setText("Failed");
            holder.binding.status.setTextColor(holder.binding.getRoot().getResources().getColor(R.color.red));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewholder extends RecyclerView.ViewHolder {

        TransactionAdapterBinding binding;

        public viewholder(TransactionAdapterBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
