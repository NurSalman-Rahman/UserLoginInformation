package com.example.login_userinformation;





import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.login_userinformation.DatabaseHelper.COL_2;


public class MainActivity extends AppCompatActivity {
    private TextView tvRegister;
    private EditText etLoginGmail, etLoginPassword;
    private Button loginButton;

    private SQLiteDatabase db;
    private SQLiteOpenHelper openHelper;
    private Cursor cursor;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
        initFunction();
        initListener();
    }

    private void initListener() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etLoginGmail.getText().toString().trim();
                String password = etLoginPassword.getText().toString().trim();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter your Email and Password to login", Toast.LENGTH_SHORT).show();
                } else {
                    cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL_4 + "=? AND " + DatabaseHelper.COL_5 + "=?", new String[]{email, password});
                    if (cursor != null) {
                        if (cursor.getCount() > 0) {
                            if (cursor.moveToFirst()) {
                                do {
                                    name = cursor.getString(cursor.getColumnIndex(COL_2));
                                    //String name =    cursor.getString(cursor.getColumnIndex(COL_3));
                                    Log.d("name", "onClick: " + name);

                                } while (cursor.moveToNext());
                            }

                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
                            String currentDateandTime = sdf.format(new Date());

                            ContentValues contentValues = new ContentValues();

                            contentValues.put(DatabaseHelper.COL_TIME_2, email);
                            contentValues.put(DatabaseHelper.COL_TIME_4, name);
                            contentValues.put(DatabaseHelper.COL_TIME_3, currentDateandTime);


                            long id = db.insert(DatabaseHelper.TABLE_NAME_TIME, null, contentValues);

                            startActivity(new Intent(MainActivity.this, LoginSucess.class));
                            Toast.makeText(getApplicationContext(), "Login sucess", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getApplicationContext(), "Login error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });


        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "ff", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(com.example.login_userinformation.MainActivity.this,com.example.login_userinformation.RegisterActivity.class));
                        finish();
            }
        });
    }

    private void initFunction() {
        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();
    }

    private void initComponent() {
        tvRegister = findViewById(R.id.tvRegister);
        etLoginGmail = findViewById(R.id.etLogGmail);
        etLoginPassword = findViewById(R.id.etLoginPassword);
        loginButton = findViewById(R.id.btnLogin);
    }
}
