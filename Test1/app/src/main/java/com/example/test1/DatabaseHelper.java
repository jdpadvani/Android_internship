package com.example.test1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper  extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Register.db";
    public static final String TABLE_NAME="User_Table";
    public static final String COL_1="FIRST_NAME";
    public static final String COL_2="LAST_NAME";
    public static final String COL_3="MOBILE";
    public static final String COL_4="MAIL";
    public static final String COL_5="PASSWORD";
    public static final String COL_6="DOB";



    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLE_NAME+"(FIRST_NAME TEXT,LAST_NAME TEXT,MOBILE INTEGER PRIMARY KEY,MAIL TEXT,PASSWORD PASSWORD,DOB Date )");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

}
