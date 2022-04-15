package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    EditText edtEmail,edtPassword,edtMobile,edtUsername,edtUserid;
    Button btnSubmit,btnUpdate,btnDelete;
    DatabaseHandler db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHandler(this);

        edtEmail=findViewById(R.id.edtEmail);
        edtPassword=findViewById(R.id.edtPassword);
        edtMobile=findViewById(R.id.edtMobile);
        edtUsername=findViewById(R.id.edtUsername);
        edtUserid=findViewById(R.id.edtUserid);

        btnSubmit=findViewById(R.id.btnSubmit);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnDelete=findViewById(R.id.btnDelete);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                    db.addContact(edtEmail.getText().toString(),
                    edtPassword.getText().toString(),edtMobile.getText().toString(),edtUsername.getText().toString());

                Intent i=new Intent(RegisterActivity.this,RecycleActivity.class);
                startActivity(i);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    db.updateContact(Integer.parseInt(edtUserid.getText().toString()),edtEmail.getText().toString(),
                            edtPassword.getText().toString(),edtMobile.getText().toString(),edtUsername.getText().toString());
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteContact(Integer.parseInt(edtUserid.getText().toString()));
            }
        });
    }
}