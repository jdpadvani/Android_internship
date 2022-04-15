package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class CustomPopup extends AppCompatActivity {

    Button btnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_popup);

        btnClick=findViewById(R.id.btnClick);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUp();
            }
        });
    }

    public void showPopUp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = getLayoutInflater();

        //this is custom dialog
        //custom_popup_dialog contains textview only
        View customView = layoutInflater.inflate(R.layout.popup_layout, null);
        // reference the textview of custom_popup_dialog
        TextView tv =  customView.findViewById(R.id.txtNumber);
        SeekBar seekBar =  customView.findViewById(R.id.seekBar);
        seekBar.setProgress(50);
        tv.setText(""+seekBar.getProgress());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv.setText(""+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        builder.setView(customView);
        builder.create();
        builder.show();
    }
}