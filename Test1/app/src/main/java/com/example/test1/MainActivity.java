package com.example.test1;

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

public class MainActivity extends AppCompatActivity {
    Button submit,Register;
    EditText email,pass;
    AwesomeValidation awesomeValidation;
    DatabaseHelper mydb;

    public String email2,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb= new DatabaseHelper(this);


        submit=(Button)findViewById(R.id.btn);
        email=(EditText)findViewById(R.id.email02);
        pass=(EditText)findViewById(R.id.password02);
        Register=(Button)findViewById(R.id.Register);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);


        /*awesomeValidation.addValidation(this, R.id.email02,
                Patterns.EMAIL_ADDRESS, R.string.invalid_Email);*/
        awesomeValidation.addValidation(this, R.id.email02,
                Patterns.EMAIL_ADDRESS, R.string.invalid_Email);

       awesomeValidation.addValidation(this, R.id.password02,
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*+=?-]).{8,15}$", R.string.invalid_Password);

         email2=getIntent().getStringExtra("mail");
         password=getIntent().getStringExtra("password");


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(MainActivity.this, Register.class);

                startActivity(i);

            }
        });


            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (awesomeValidation.validate()) {

                        String mail = email.getText().toString();
                        String Pass = pass.getText().toString();

                        Log.i(mail,"received");
                        Log.i(Pass,"Recieved");

                        if(mail.equals(email2) && Pass.equals(password))
                        {
                            Toast.makeText(getApplicationContext(), "Login Successfully...",
                                    Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(MainActivity.this,Third.class);
                            i.putExtra("EMAIL",email2);
                            i.putExtra("PASSWORD",password);
                            startActivity(i);

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Are You Register User !...",
                                    Toast.LENGTH_SHORT).show();
                        }

                      // Intent i = new Intent(MainActivity.this, Third.class);
                      //  i.putExtra("mail",mail);
                       // i.putExtra("password",Pass);
                      //  startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Login failed..",
                                Toast.LENGTH_SHORT).show();
                    }

                }
            });


    }
}