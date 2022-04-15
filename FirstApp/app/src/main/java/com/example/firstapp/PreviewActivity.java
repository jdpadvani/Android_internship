package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class PreviewActivity extends AppCompatActivity {

    ImageView imgPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        imgPreview= findViewById(R.id.imgPreview);

        String path = getIntent().getStringExtra("PATH");
        imgPreview.setImageBitmap(BitmapFactory.decodeFile(path));
    }
}