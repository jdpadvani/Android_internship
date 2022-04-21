package com.example.eshop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // variables of Animation
    Animation topanim,bottomanim;
    ImageView image;
    TextView txt;

    private static int SPLASH_SCREEN=5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        // call the our aniomation
        topanim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomanim=AnimationUtils.loadAnimation(this,R.anim.bottom_animation);


        image=findViewById(R.id.img1);
        txt=findViewById(R.id.txt2);

        //animation assign
        image.setAnimation(topanim);
        txt.setAnimation(bottomanim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(MainActivity.this,Login.class);

                Pair[] pairs=new Pair[2];
                pairs[0]=new Pair<View,String>(image,"image");
                pairs[1]=new Pair<View,String>(txt,"txt");

                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
                startActivity(i,options.toBundle());
            }
        },SPLASH_SCREEN);
    }
}