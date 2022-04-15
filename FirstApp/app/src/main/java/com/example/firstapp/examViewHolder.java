package com.example.firstapp;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class examViewHolder extends RecyclerView.ViewHolder
{
    TextView txtName;
    TextView txtEmail;
    TextView txtMobile;
    View view;

    public examViewHolder(View itemView) {
        super(itemView);

        txtName=itemView.findViewById(R.id.txtName);
        txtEmail=itemView.findViewById(R.id.txtEmail);
        txtMobile=itemView.findViewById(R.id.txtMobile);

        view=itemView;
    }
}
