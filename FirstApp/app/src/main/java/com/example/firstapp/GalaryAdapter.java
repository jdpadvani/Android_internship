package com.example.firstapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GalaryAdapter extends RecyclerView.Adapter<GalaryViewHolder>
{
    ArrayList<String> list;
    Context context;

    public GalaryAdapter(ArrayList<String> list, Context context)
    {
        this.list = list;
        this.context = context;
    }

    @Override
    public GalaryViewHolder
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
                .inflate(R.layout.raw_gallary,
                        parent, false);

        GalaryViewHolder viewHolder
                = new GalaryViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void
    onBindViewHolder(final GalaryViewHolder viewHolder,final int position)
    {

        viewHolder.imgPhoto.setVisibility(View.VISIBLE);

//        final index = viewHolder.getAdapterPosition();
        Bitmap myBitmap = BitmapFactory.decodeFile(list.get(position));
        viewHolder.imgPhoto.setImageBitmap(myBitmap);

        viewHolder.imgPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(context,PreviewActivity.class);
                i.putExtra("PATH",list.get(position));
                context.startActivity(i);
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
