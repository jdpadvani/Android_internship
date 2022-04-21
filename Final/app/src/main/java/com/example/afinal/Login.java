package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public class Login extends AppCompatActivity {

    Button login;
    EditText email,pass;
    AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login=(Button)findViewById(R.id.btn);
        email=(EditText)findViewById(R.id.email02);
        pass=(EditText)findViewById(R.id.password02);

        String email2=getIntent().getStringExtra("mail");
        String password=getIntent().getStringExtra("password");

        awesomeValidation.addValidation(this, R.id.email02,
                Patterns.EMAIL_ADDRESS, R.string.invalid_Email);
        awesomeValidation.addValidation(this, R.id.password02,
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*+=?-]).{8,15}$", R.string.invalid_Password);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail01 = email.getText().toString();
                String Pass01 = pass.getText().toString();


                /*Log.i(email2,"aavi gyo intent thi aavya a ");
                Log.i(password,"aa pan aavi gyo intent wada");

                Log.i(mail01,"Login wada page ma nakhi a");
                Log.i(Pass01,"tane kidhu to khara page wada 6i");

                Toast.makeText(getApplicationContext(), "Data aavi gya bhura..",
                        Toast.LENGTH_SHORT).show();*/

                if (awesomeValidation.validate())

                {
                    Toast.makeText(getApplicationContext(), "Register Successfully...",
                            Toast.LENGTH_SHORT).show();
                    String mail = email.getText().toString();
                    String Pass = pass.getText().toString();

                    Log.i(mail, "received");
                    Log.i(Pass, "Recieved");

                    Intent i = new Intent(Login.this, Your_Activity.class);
                    i.putExtra("mail", mail);
                    i.putExtra("password", Pass);
                    startActivity(i);

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Register failed..",
                            Toast.LENGTH_SHORT).show();
                }


                if(mail01.equals(email2) && Pass01.equals(password))
                {
                    Toast.makeText(getApplicationContext(), "Login Successfully...",
                            Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login.this,Your_Activity.class);
                    i.putExtra("EMAIL",email2);
                    i.putExtra("PASSWORD",password);
                    startActivity(i);

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "something Went Wrong !...",
                            Toast.LENGTH_SHORT).show();
                }

            }


        });
    }
}