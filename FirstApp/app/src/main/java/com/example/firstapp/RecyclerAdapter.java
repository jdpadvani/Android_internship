package com.example.firstapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<examViewHolder>
{
    ArrayList<UseModel> list;
    Context context;

    public RecyclerAdapter(ArrayList<UseModel> list,Context context)
    {
        this.list = list;
        this.context = context;
    }

    @Override
    public examViewHolder
    onCreateViewHolder(ViewGroup parent,
                       int viewType)
    {

        Context context
                = parent.getContext();
        LayoutInflater inflater
                = LayoutInflater.from(context);

        // Inflate the layout

        View photoView
                = inflater
                .inflate(R.layout.raw,
                        parent, false);

        examViewHolder viewHolder
                = new examViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void
    onBindViewHolder(final examViewHolder viewHolder,final int position)
    {
//        final index = viewHolder.getAdapterPosition();
        viewHolder.txtName
                .setText(list.get(position).getUsername());
        viewHolder.txtEmail
                .setText(list.get(position).getEmail());
        viewHolder.txtMobile
                .setText(list.get(position).getMobile());
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

            }
        });
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(
            RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
