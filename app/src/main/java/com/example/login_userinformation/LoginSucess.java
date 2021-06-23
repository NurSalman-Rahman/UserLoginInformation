package com.example.login_userinformation;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import static com.example.login_userinformation.DatabaseHelper.COL_TIME_2;
import static com.example.login_userinformation.DatabaseHelper.COL_TIME_3;
import static com.example.login_userinformation.DatabaseHelper.COL_TIME_4;


public class LoginSucess extends AppCompatActivity {

    private SQLiteDatabase db;
    private SQLiteOpenHelper openHelper;
    private Cursor cursor;
    private TextView showTimeUser;
    ArrayList<LoginData> loginData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sucess);
        initComponent();
        initFunction();
    }

    private void initComponent() {
        showTimeUser = findViewById(R.id.show_time_user);
    }

    private void initFunction() {
        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME_TIME, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        loginData.add(new LoginData(cursor.getString(cursor.getColumnIndex(COL_TIME_4)), cursor.getString(cursor.getColumnIndex(COL_TIME_3)), cursor.getString(cursor.getColumnIndex(COL_TIME_2))));
                        //String name =    cursor.getString(cursor.getColumnIndex(COL_3));
                        Log.d("nameUser", "onClick: " + cursor.getString(cursor.getColumnIndex(COL_TIME_4)));
                        showTimeUser.setText("You are now logged in from \nName:- " + cursor.getString(cursor.getColumnIndex(COL_TIME_4)) + " \nEmail:- " + cursor.getString(cursor.getColumnIndex(COL_TIME_2)) + " \nTime:- " + cursor.getString(cursor.getColumnIndex(COL_TIME_3)));
                    } while (cursor.moveToNext());
                }

            } else {
                Toast.makeText(getApplicationContext(), "Login error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
