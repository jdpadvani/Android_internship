package com.example.practiceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.PatternsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import java.util.regex.Pattern;

import static android.view.View.Z;


public class MainActivity extends AppCompatActivity {

    Button Register;
    EditText etname,etmail,etpass,etmobile;
    AwesomeValidation awesomeValidation;
   // private Object ValidationStyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Register=(Button)findViewById((R.id.btnrg));
        etname=(EditText)findViewById((R.id.fname));
        etmail=(EditText)findViewById(R.id.edtemail);
        etmobile=(EditText)findViewById(R.id.edtmb);
        etpass=(EditText)findViewById(R.id.edtps);

        awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this,R.id.fname,
                "[ .a-zA-Z]+",R.string.invalid_name);
        awesomeValidation.addValidation(this,R.id.edtemail,
                Patterns.EMAIL_ADDRESS,R.string.invalid_Email);
        awesomeValidation.addValidation(this,R.id.edtmb,
                "[5-9]{1}[0-9]{9}$",R.string.invalid_Number);
        awesomeValidation.addValidation(this,R.id.edtps,
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*+=?-]).{8,15}$",R.string.invalid_Password);



       Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(awesomeValidation.validate())
                {
                    Toast.makeText(getApplicationContext(),"Register Successfully...",
                            Toast.LENGTH_SHORT).show();
                    String str=etname.getText().toString();
                    String mail=etmail.getText().toString();
                    String mobile=etmobile.getText().toString();
                    String Pass=etpass.getText().toString();
                    Intent i=new Intent(MainActivity.this,ActivityTwo.class);

                    i.putExtra("Name",str);
                    i.putExtra("Mail",mail);
                    i.putExtra("Mobile",mobile);
                    i.putExtra("Password",Pass);

                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Register Failed..",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this,"selected item"+item.getTitle(),Toast.LENGTH_SHORT)
                .show();
        switch (item.getItemId())
        {
            case R.id.search_item:
            return true;

            case R.id.upload_item:
            return true;

            case R.id.copy_item:
            return true;

            case R.id.print_item:
            return true;

            case R.id.share_item:
            return true;

            case R.id.bookmark_item:
             return true;

            default:
            return super.onOptionsItemSelected(item);
        }

    }
}