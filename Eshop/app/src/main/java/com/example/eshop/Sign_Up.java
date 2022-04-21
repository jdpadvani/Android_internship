package com.example.eshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.ref.Reference;

public class Sign_Up extends AppCompatActivity {

    TextInputLayout regname,regemail,regmobile,regpass;
    Button signup,login;
    AwesomeValidation awesomeValidation;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);

        //hooks
        regname=findViewById(R.id.name);
        regemail=findViewById(R.id.email2);
        regmobile=findViewById(R.id.mobile);
        regpass=findViewById(R.id.Password);
        signup=findViewById(R.id.SignUp);
        login=findViewById(R.id.login);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.name,
                "[ .a-zA-Z]+", R.string.invalid_name);
        awesomeValidation.addValidation(this, R.id.email2,
                Patterns.EMAIL_ADDRESS, R.string.invalid_Email);
        awesomeValidation.addValidation(this, R.id.mobile,
                "[5-9]{1}[0-9]{9}$", R.string.invalid_Number);
        awesomeValidation.addValidation(this, R.id.Password,
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*+=?-]).{8,15}$", R.string.invalid_Password);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //FirebaseDatabase.getInstance().getReference().child("Programing").child("Android").setValue("abcd");
                if (awesomeValidation.validate()) {
                    Toast.makeText(getApplicationContext(), "Register Successfully...",
                            Toast.LENGTH_SHORT).show();
                    String name=regname.getEditText().getText().toString();
                    String mail = regemail.getEditText().getText().toString();
                    String Pass = regpass.getEditText().getText().toString();
                    String mobile=regmobile.getEditText().getText().toString();

                    Log.i(mail,"received");
                    Log.i(Pass,"Recieved");

                    Intent i = new Intent(Sign_Up.this, Login.class);
                    i.putExtra("Username",name);
                    i.putExtra("mail",mail);
                    i.putExtra("password",Pass);
                    i.putExtra("mobile",mobile);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Register failed..",
                            Toast.LENGTH_SHORT).show();
                }

             /*   rootNode=FirebaseDatabase.getInstance();
                reference=rootNode.getReference("https://eshop-e3c71-default-rtdb.firebaseio.com/Users");

                String name=regname.getEditText().getText().toString();
                String mail = regemail.getEditText().getText().toString();
                String Pass = regpass.getEditText().getText().toString();
                String mobile=regmobile.getEditText().getText().toString();

                Helper helper=new Helper(name,mail,Pass,mobile);

                reference.setValue("data Storage");*/



            }
        });

    }
}