package com.example.login_userinformation;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
        public static final String DATABASE_NAME="register.db";
        public static final String TABLE_NAME="registration";
        public static final String COL_1="ID";
        public static final String COL_2="Name";
        public static final String COL_3="Phone";
        public static final String COL_4="Gmail";
        public static final String COL_5="Password";


    public static final String TABLE_NAME_TIME="loginTable";
    public static final String COL_TIME_1="ID";
    public static final String COL_TIME_2="LoginID";
    public static final String COL_TIME_3="LoginTime";
    public static final String COL_TIME_4="Name";


        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,Phone TEXT,Gmail TEXT,Password TEXT)");
            db.execSQL("CREATE TABLE " + TABLE_NAME_TIME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,LoginID TEXT,LoginTime TEXT,Name TEXT)");

        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME_TIME);
            onCreate(db);
        }
    }

