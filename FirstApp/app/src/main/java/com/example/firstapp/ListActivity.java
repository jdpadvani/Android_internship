package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ArrayList<String> alName=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

//        alName=getIntent().getStringArrayListExtra("AL");

        for (int i=0;i<alName.size();i++)
        {
            Log.i("Name",""+alName.get(i));
        }
    }
}