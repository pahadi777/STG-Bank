package com.example.stgbank.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stgbank.Models.User;
import com.example.stgbank.databinding.ActivityAllUsersBinding;
import com.example.stgbank.databinding.AllUserAdapterBinding;

import java.util.ArrayList;

public class AllUserAdapter extends RecyclerView.Adapter<AllUserAdapter.viewholder>
{

    ArrayList<User> list;
    private static Clicklistener clicklistener;

    public AllUserAdapter(ArrayList<User> list)
    {
        this.list = list;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        AllUserAdapterBinding binding = AllUserAdapterBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position)
    {
        User user = list.get(position);
        holder.binding.entername.setText(user.getName());
        holder.binding.entermail.setText(user.getMail());
        holder.binding.enternumber.setText(user.getPhone());
        holder.binding.balance.setText(String.valueOf(user.getBalance()));
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    public static class viewholder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        AllUserAdapterBinding binding;

        public viewholder(AllUserAdapterBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            clicklistener.onitemclick(getAdapterPosition(),view);
        }
    }

    public void setonitemclicklistener(Clicklistener clicklistener)
    {
        AllUserAdapter.clicklistener = clicklistener;
    }

    public interface Clicklistener
    {
        void onitemclick(int position , View v);
    }
}
