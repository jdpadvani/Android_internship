package com.example.firstapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

public class GalaryViewHolder extends RecyclerView.ViewHolder
{
    AppCompatImageView imgPhoto;
    View view;

    public GalaryViewHolder(View itemView) {
        super(itemView);

        imgPhoto=itemView.findViewById(R.id.imgPhoto);
        view=itemView;
    }
}
