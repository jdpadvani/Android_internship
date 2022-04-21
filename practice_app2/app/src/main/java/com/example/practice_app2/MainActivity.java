package com.example.practice_app2;

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
    Button Register;
    EditText etname,etpass,etmail,etmobile;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Register = (Button) findViewById((R.id.btnrg));
        etname = (EditText) findViewById((R.id.fname));
        etmail = (EditText) findViewById(R.id.edtemail);
        etmobile = (EditText) findViewById(R.id.edtmb);
        etpass = (EditText) findViewById(R.id.edtps);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.fname,
                "[ .a-zA-Z]+", R.string.invalid_name);
        awesomeValidation.addValidation(this, R.id.edtemail,
                Patterns.EMAIL_ADDRESS, R.string.invalid_Email);
        awesomeValidation.addValidation(this, R.id.edtmb,
                "[5-9]{1}[0-9]{9}$", R.string.invalid_Number);
        awesomeValidation.addValidation(this, R.id.edtps,
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*+=?-]).{8,15}$", R.string.invalid_Password);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (awesomeValidation.validate()) {
                    Toast.makeText(getApplicationContext(), "Register Successfully...",
                            Toast.LENGTH_SHORT).show();
                    String mail = etmail.getText().toString();
                    String Pass = etpass.getText().toString();

                    Log.i(mail,"received");
                    Log.i(Pass,"Recieved");

                    Intent i = new Intent(MainActivity.this, Login_Activity.class);
                    i.putExtra("mail",mail);
                    i.putExtra("password",Pass);
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