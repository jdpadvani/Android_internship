package com.example.firstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.io.File;

public class CameraGalaryActivity extends AppCompatActivity {

    AppCompatImageView imgView;
    Button btnCamera,btnGallary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_galary);

        imgView=findViewById(R.id.imgView);
        btnCamera=findViewById(R.id.btnCamera);
        btnGallary=findViewById(R.id.btnGallary);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
//                checkPermissionCamera(Manifest.permission.CAMERA);
//                showAlert();
                showPopup();
            }
        });

        btnGallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionGallary(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        });
    }

    public void checkPermissionCamera
            (String permission)
    {
        // Checking if permission is not granted
        if (ContextCompat.checkSelfPermission(CameraGalaryActivity.this, permission) == PackageManager.PERMISSION_DENIED)
        {
            ActivityCompat.requestPermissions(CameraGalaryActivity.this, new String[] { permission },11);
        }
        else
        {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, 11);
        }
    }

    public void checkPermissionGallary(String permission)
    {
        // Checking if permission is not granted
        if (ContextCompat.checkSelfPermission(CameraGalaryActivity.this, permission) == PackageManager.PERMISSION_DENIED)
        {
            ActivityCompat.requestPermissions(CameraGalaryActivity.this, new String[] { permission },11);
        }
        else
        {
            Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, 22);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 10)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 11);
            }
            else {
                Toast.makeText(CameraGalaryActivity.this, "Storage Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 22);
            }
            else {
                Toast.makeText(CameraGalaryActivity.this, "Storage Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 11 && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imgView.setImageBitmap(photo);
        }
        if (requestCode == 22 && resultCode == RESULT_OK && null != data)
        {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn,
                    null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            imgView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }

    public void showAlert()
    {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
//set icon
                .setIcon(android.R.drawable.stat_notify_error)
//set title
                .setTitle("Select Image")
//set message
//                .setMessage("Exiting will call finish() method")
//set positive button
                .setPositiveButton("Camera", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what would happen when positive button is clicked
                        checkPermissionCamera(Manifest.permission.CAMERA);
                        finish();
                    }
                })
//set negative button
                .setNegativeButton("Gallary", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what should happen when negative button is clicked
                        checkPermissionGallary(Manifest.permission.READ_EXTERNAL_STORAGE);
                    }
                })
                .show();
    }

    private void showPopup()
    {
        Button btnSelect;
// inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)  getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popuplayout, null);
        btnSelect=popupView.findViewById(R.id.btnSelect);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Click","Select");
            }
        });
    }
}