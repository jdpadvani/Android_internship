package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    ArrayList<String> alName=new ArrayList<>();
    Button btnClick,btnSave;
    EditText edtEmail,edtPassword;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String email = "email";
    public static final String pass = "pass";

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        btnClick = findViewById(R.id.btnSubmit);
        btnSave = findViewById(R.id.btnSave);

        btnClick.setOnClickListener(this);
        btnSave.setOnClickListener(this);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.btnSave)
        {
            if(checkValidation(edtEmail.getText().toString(),edtPassword.getText().toString()))
            {
               alName.add(edtEmail.getText().toString()) ;
            }
        }
        else
        {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(email, edtEmail.getText().toString());
            editor.putString(pass, edtPassword.getText().toString());
            editor.apply();

            Intent i=new Intent(MainActivity.this, ActivityTwo.class);
            i.putExtra("AL",alName);
            startActivity(i);
        }
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static boolean isValidPassword(final String password)
    {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean checkValidation(String strEmail,String strPass)
    {
        if(strEmail.matches(""))
        {
//                Toast.makeText(MainActivity.this,"Enter Value of Email",Toast.LENGTH_LONG).show();
            edtEmail.setError("Enter Value of Email");
            return false;
        }
        else if(!isValidEmail(strEmail))
        {
            edtEmail.setError("Enter valid Email");
            return false;
        }
        else if(!isValidPassword(strPass))
        {
            edtPassword.setError("Enter valid password");
            return false;
        }
        else if(strPass.matches("")){
//                Toast.makeText(MainActivity.this,"Enter Value of Password",Toast.LENGTH_LONG).show();
            edtPassword.setError("Enter Value of Password");
            return false;
        }
        else
        {
            return true;
        }
    }
}