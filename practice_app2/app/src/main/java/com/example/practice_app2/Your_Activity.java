package com.example.practice_app2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class Your_Activity extends AppCompatActivity {

    TextView txtfirst,txtsecond;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_);

        txtfirst=(TextView)findViewById(R.id.txt1);
        txtsecond=(TextView)findViewById(R.id.txt2);

        String email2=getIntent().getStringExtra("EMAIL");
        String password=getIntent().getStringExtra("PASSWORD");


        txtfirst.setText(email2);
        txtsecond.setText(password);

        Log.i(email2,"REGISTER MATHI AAVELA GUEST");
        Log.i(password,"BHAI KETLI VAR KEVANU REGISTER GAM NA 6e");


    }

}