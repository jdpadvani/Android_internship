package com.example.firstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class MediaActivity extends AppCompatActivity
{
    VideoView videoView;
    String URL = "https://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        videoView = findViewById(R.id.videoView);

        // Raw foldere
        String path = "android.resource://" + getPackageName() + "/" + R.raw.sample;
//        videoView.setVideoURI(Uri.parse(path));

        // From URL
//        videoView.setVideoPath(URL);
//        videoView.start();
//        videoView.setMediaController(new MediaController(this));

        checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    public void checkPermission(String permission)
    {
        // Checking if permission is not granted
        if (ContextCompat.checkSelfPermission(MediaActivity.this, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(MediaActivity.this, new String[] { permission },
                    10);
        }
        else
            {
            String path = Environment.getStorageDirectory().toString()
                    +"/emulated/0/m/VID-20220326-WA0004.mp4";

            File f=new File(path);
            if(f.exists())
            {
                videoView.setVideoPath(f.getAbsolutePath());
                videoView.start();
            }
            else
            {
                Log.i("Video exist","Not exist");
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode,@NonNull String[] permissions,@NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 10)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                File f=new File("/storage/emulated/0/m/VID-20220326-WA0004.mp4");
                if(f.exists())
                {
                    videoView.setVideoPath(f.getAbsolutePath());
                    videoView.start();
                }
                else
                {
                    Log.i("File not found","File");
                }
            }
            else {
                Toast.makeText(MediaActivity.this, "Storage Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}