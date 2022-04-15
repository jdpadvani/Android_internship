package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SpalshScreen extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh_screen);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        String val = sharedpreferences.getString(MainActivity.email,"No email value");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!val.matches("No email value"))
                {
                    Intent i=new Intent(SpalshScreen.this,ActivityTwo.class);
                    startActivity(i);
                }
                else {
                    Intent i=new Intent(SpalshScreen.this,MainActivity.class);
                    startActivity(i);
                }
            }
        }, 10000);


    }
}