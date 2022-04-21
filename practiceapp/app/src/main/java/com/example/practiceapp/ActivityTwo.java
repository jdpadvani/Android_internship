package com.example.practiceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityTwo extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    Button btn1;
    TextView name,EMail,Mobile,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        btn1 = (Button) findViewById(R.id.Button01);
        name=(TextView)findViewById(R.id.TextView01);
        EMail=(TextView)findViewById(R.id.TextView02);
        Mobile=(TextView)findViewById(R.id.TextView03);
        pass=(TextView)findViewById(R.id.TextView04);

        Intent intent=getIntent();
        String str = intent.getStringExtra("Name");
        String Mail = intent.getStringExtra("EMail");
        String mobile = intent.getStringExtra("Mobile");
        String Pass = intent.getStringExtra("Password");
        name.setText(str);

        EMail.setText(Mail);

        Mobile.setText(mobile);

        pass.setText(Pass);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActivityTwo.this, MainActivity.class);
                PopupMenu popup = new PopupMenu(ActivityTwo.this, view);
                popup.setOnMenuItemClickListener(ActivityTwo.this);
                popup.inflate(R.menu.popup_menu);
                popup.show();
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        Toast.makeText(this,"Selected item"+ menuItem.getTitle(),Toast.LENGTH_SHORT)
                .show();
        switch (menuItem.getItemId())
        {
            case R.id.search_item:
                return  true;

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
                return false;
        }

    }
}