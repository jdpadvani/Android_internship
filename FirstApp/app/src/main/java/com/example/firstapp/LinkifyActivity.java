package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;

public class LinkifyActivity extends AppCompatActivity {

    TextView txtLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linkify);

        txtLink=findViewById(R.id.txtLink);
//        txtLink.setText("https://www.tutorialspoint.com/");
//        txtLink.setText("+919558751613");
//        txtLink.setText("test@gmail.com");

        txtLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                // OPEN URL
//                String url = "https://www.tutorialspoint.com/";
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(url));
//                startActivity(i);

                // CALL
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:0123456789"));
//                startActivity(intent);

                // EMAIL
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL  , new String[] { "me@somewhere.com" });
                intent.putExtra(Intent.EXTRA_SUBJECT, "My subject");
                startActivity(Intent.createChooser(intent, "Email via..."));
            }
        });
    }
}