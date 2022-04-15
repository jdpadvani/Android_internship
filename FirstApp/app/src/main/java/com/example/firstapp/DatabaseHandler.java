package com.example.firstapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    // Database version & name & table name
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contactsManager";
    private static final String TABLE_CONTACTS = "contacts";

    // Table column / Fields
    private static final String ID = "id";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String MOBILE = "mobile";
    private static final String USER_NAME = "username";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE "
                + TABLE_CONTACTS + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + EMAIL + " TEXT,"
                + PASSWORD + " TEXT,"
                + MOBILE + " TEXT,"
                + USER_NAME + " TEXT"
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
    }

    void addContact(String email, String pass, String Mobile, String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EMAIL, email); // Contact Name
        values.put(PASSWORD, pass); // Contact Phone
        values.put(MOBILE, Mobile); // Contact Phone
        values.put(USER_NAME, username); // Contact Phone
        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    public ArrayList<UseModel> getAllContacts() {
        ArrayList<UseModel> contactList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                UseModel m = new UseModel();
                m.setId(Integer.parseInt(cursor.getString(0)));
                m.setEmail(cursor.getString(1));
                m.setPassword(cursor.getString(2));
                m.setMobile(cursor.getString(3));
                m.setUsername(cursor.getString(4));
                // Adding contact to list
                contactList.add(m);
            } while (cursor.moveToNext());
        }
        return contactList;
    }

    public boolean updateContact
            (Integer id, String email, String pass, String mobile, String username)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EMAIL, email);
        contentValues.put(PASSWORD, pass);
        contentValues.put(MOBILE, mobile);
        contentValues.put(USER_NAME, username);
        db.update(TABLE_CONTACTS, contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_CONTACTS,
                "id = ? ",
                new String[] { Integer.toString(id) });
    }
}
