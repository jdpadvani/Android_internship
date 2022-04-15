package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;

public class GalalryActivity extends AppCompatActivity
{
    RecyclerView recyclerGallary;
    GalaryAdapter adapter;
    ArrayList<String> alImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galalry);

        alImage=new ArrayList<>();
        alImage=getAllShownImagesPath(GalalryActivity.this);

        Log.i("Size",""+alImage.size());

        recyclerGallary=findViewById(R.id.recyclerGallary);
        adapter=new GalaryAdapter(alImage,GalalryActivity.this);
        recyclerGallary.setAdapter(adapter);
        recyclerGallary.setLayoutManager(new GridLayoutManager(GalalryActivity.this, 3));
    }

    private ArrayList<String> getAllShownImagesPath(Activity activity)
    {
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;
        ArrayList<String> listOfAllImages = new ArrayList<String>();
        String absolutePathOfImage = null;
        uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = { MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME };

        cursor = activity.getContentResolver().query(uri, projection, null,
                null, null);

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext())
        {
            absolutePathOfImage = cursor.getString(column_index_data);
            listOfAllImages.add(absolutePathOfImage);
        }
        return listOfAllImages;
    }
}