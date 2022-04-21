package com.example.eshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.database.AbstractWindowedCursor;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    Button SignUp, Login;
    ImageView image;
    TextView txt, txt2;
    private TextInputLayout email1, pass1;
    public String email2,password;
    private FirebaseAuth auth;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //This is use to hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
// this is use to hooks
        SignUp = findViewById(R.id.SignUp);
        image = findViewById(R.id.img2);
        txt = findViewById(R.id.text3);
        txt2 = findViewById(R.id.text4);
        email1 = findViewById(R.id.email1);
        pass1 = findViewById(R.id.pass1);
        Login=findViewById(R.id.login);

        auth= FirebaseAuth.getInstance();

         email2 = getIntent().getStringExtra("mail");
         password = getIntent().getStringExtra("password");

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.email1,
                Patterns.EMAIL_ADDRESS, R.string.invalid_Email);

        awesomeValidation.addValidation(this, R.id.pass1,
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*+=?-]).{8,15}$", R.string.invalid_Password);
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(Login.this, Sign_Up.class);

                Pair[] pairs = new Pair[6];
                pairs[0] = new Pair<View, String>(image, "image");
                pairs[1] = new Pair<View, String>(txt, "txt");
                pairs[2] = new Pair<View, String>(txt2, "txt2");
                pairs[3] = new Pair<View, String>(email1, "email");
                pairs[4] = new Pair<View, String>(pass1, "password");
                pairs[5] = new Pair<View, String>(SignUp, "signup");


                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
                startActivity(i, options.toBundle());
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (awesomeValidation.validate()) {
                    /*Toast.makeText(getApplicationContext(), "Register Successfully...",
                            Toast.LENGTH_SHORT).show();*/
                    String mail01 = email1.getEditText().getText().toString();
                    String Pass01 = pass1.getEditText().getText().toString();

                    if(mail01.equals(email2) && Pass01.equals(password))
                    {
                        Toast.makeText(getApplicationContext(), "Login Successfully...",
                                Toast.LENGTH_SHORT).show();
                        LoginUser(mail01,Pass01);
                        Intent i = new Intent(Login.this,UserProfile.class);
                        i.putExtra("EMAIL",email2);
                        i.putExtra("PASSWORD",password);
                        startActivity(i);

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "ARE YOU REGISTER USER? !...",
                                Toast.LENGTH_SHORT).show();

                    }

                    //Intent i = new Intent(Login.this, Login.class);
                    //i.putExtra("Username",name);
                    //i.putExtra("mail",mail01);
                    //i.putExtra("password",Pass01);
                    //i.putExtra("mobile",mobile);
                    //startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Register failed..",
                            Toast.LENGTH_SHORT).show();
                }



            }
        });
    }

   private void LoginUser(String mail01, String Pass01) {

        auth.createUserWithEmailAndPassword(mail01,Pass01).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Login.this,"Login successfull",Toast.LENGTH_SHORT).show();
                }
                else{

                    Toast.makeText(Login.this,"Login Failed",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


}
