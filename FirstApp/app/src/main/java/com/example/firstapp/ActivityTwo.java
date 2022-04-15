package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityTwo extends AppCompatActivity
{
    TextView txtEmail,txtPassword;
    String strEmail,strPass;
    Button btnLogout;

    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        txtEmail=findViewById(R.id.txtEmail);
        txtPassword=findViewById(R.id.txtPassword);
        btnLogout = findViewById(R.id.btnLogout);

//        strEmail=getIntent().getStringExtra("Name");
//        strPass=getIntent().getStringExtra("Pass");


        strEmail =sharedpreferences.getString(MainActivity.email,"No email value");
        strPass = sharedpreferences.getString(MainActivity.pass,"No pass value");

        txtEmail.setText(strEmail);
        txtPassword.setText(strPass);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("INFO","INFO");
                Log.e("Error","Error");
                Log.d("Debug","Debug");
                Log.v("Verbose","Verbose");

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();

                Intent i=new Intent(ActivityTwo.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}