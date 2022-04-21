package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public class Register extends AppCompatActivity {
    EditText fname,lname,edtmb,edtps,dob,edtemail;
    Button btnrg,btnimg;

    AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fname=findViewById(R.id.fname);
        lname=findViewById(R.id.lname);
        edtemail=findViewById(R.id.edtemail);
        edtmb=findViewById(R.id.edtmb);
        edtps=findViewById(R.id.edtps);
        dob=findViewById(R.id.dob);
        btnrg=findViewById(R.id.btnrg);
        btnimg=findViewById(R.id.btnimg);

        awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.fname,
                "[ .a-zA-Z]+", R.string.invalid_name);
        awesomeValidation.addValidation(this, R.id.lname,
                "[ .a-zA-Z]+", R.string.invalid_name);
        awesomeValidation.addValidation(this, R.id.edtemail,
                Patterns.EMAIL_ADDRESS, R.string.invalid_Email);
        awesomeValidation.addValidation(this, R.id.edtmb,
                "[5-9]{1}[0-9]{9}$", R.string.invalid_Number);

        awesomeValidation.addValidation(this, R.id.edtps,
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*+=?-]).{8,15}$", R.string.invalid_Password);

        awesomeValidation.addValidation(this, R.id.dob, "^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$", R.string.Invalid_Date);

       /* btnimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/


        btnrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(awesomeValidation.validate())
                {
                    Toast.makeText(getApplicationContext(), "Register Successfully...",
                            Toast.LENGTH_SHORT).show();
                    String name=fname.getText().toString();
                    String lastname=lname.getText().toString();
                    String mail = edtemail.getText().toString();
                    String Pass = edtps.getText().toString();
                    String Date=dob.getText().toString();
                    String mobile=edtmb.getText().toString();

                    Intent i = new Intent(Register.this, MainActivity.class);
                    i.putExtra("Firstname",name);
                    i.putExtra("Lastname",lastname);
                    i.putExtra("mail",mail);
                    i.putExtra("password",Pass);
                    i.putExtra("mobile",mobile);
                    i.putExtra("Date Of Birth",Date);
                    startActivity(i);

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Register failed..",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}