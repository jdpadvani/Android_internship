package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Date;

public class NotificationActivity extends AppCompatActivity
{
    private static final String CHANNEL_ID_1 = "";
    private static final String CHANNEL_ID_2 = "";
    Button btnNotify;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        btnNotify=findViewById(R.id.btnNotify);
        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    addNotification();
            }
        });
    }

    private void addNotification()
    {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel1 = new
                    NotificationChannel(CHANNEL_ID_1, "Channel(1)", NotificationManager.IMPORTANCE_HIGH);
            channel1.setDescription("Channel 1");

//            NotificationChannel channel2 = new
//                    NotificationChannel(CHANNEL_ID_2, "Channel(2)", NotificationManager.IMPORTANCE_HIGH);
//            channel2.setDescription("Channel 2 Dec..");

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel1);
//            notificationManager.createNotificationChannel(channel2);
        }


        NotificationCompat.Builder notification = new NotificationCompat.Builder(this, CHANNEL_ID_2)
                .setSmallIcon(R.drawable.ic_launcher_background)
//                .setLargeIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Title")
                .setContentText("Text")
//                .setStyle(new NotificationCompat.BigPictureStyle()
//                        .bigPicture(imgUrl)
//                        .bigLargeIcon(null))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .setContentIntent(pendingIntent)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            notification.setSmallIcon(R.drawable.ic_launcher_background);
            notification.setColor(getResources().getColor(R.color.design_default_color_error));
        } else {
            notification.setSmallIcon(R.drawable.ic_launcher_background);
        }
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());

        int m = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
        notificationManager.notify(m,notification.build());
    }
}